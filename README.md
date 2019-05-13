1. Fundamental Overview of Hexagonal Architecture in Java

In an object oriented Java paradigm, objects inner to the application interact with each other. And the entities like user interface or database infrastructures interact from outside the application. In a hexagonal architecture, we imagine that a java application is similar to a Hexagon with domain logic at its core. We know it by the term Domain Driven Design. However, the architecture core is not limited to just business domain and can be anything else.Around the core, we have layers dependent on the core. This layering is what creates an n-tier application. The edges of the hexagon are interaction or integration points for outside entities like HTTP, Rest/Soap calls, database exchange or even interactions with other hexagons (other applications).In practical world, this architecture applies Port-Adapter pattern, which we will discuss next through an example.2. Understand through ExampleLet's create an n-tier shopping application using Port-Adapter pattern as we discussed. As layers, this application visibly has presentation layer, service layer and persistence Data Access Object (DAO) layer. Just remember that ports are interfaces and adapters are implementations fulfilling those interfaces. As part of Domain Driven Development, we will build this application around business interfaces ShoppingServicePort and ShoppingDaoPort. These interfaces here are the core of the hexagon which helps in defining the application further.



    ShoppingServicePortpublic interface ShoppingServicePort {
    
        public Order placeOrder(String productName);
        public void updateInventory();

    }



    ShoppingDaoPortpublic interface ShoppingDaoPort {
    
        public long saveOrder(String productName);
        public void updateInventory();

    }


Around this core of interfaces, we have their implementation classes which are adapters. Adapters ShoppingServiceImplAdapter and ShoppingDaoImplAdapter are part of 'Service & Dao' layer of hexagon . These adapters are driven by ports (interfaces). In other words, Service & Dao layer is dependent on core of hexagon.

ShoppingServiceImplAdapter


    @Service
    public class ShoppingServiceAdapter implements ShoppingServicePort {
    @Autowired
    ShoppingDaoPort shoppingDaoPort;

    @Override
    public Order placeOrder(String productName) {
        long orderId = shoppingDaoPort.saveOrder(productName);
        return new Order(orderId, productName);
    }

    @Override
    public void updateInventory() {
        shoppingDaoPort.updateInventory();
    }

}

ShoppingDaoImplAdapter



    @Repository
    public class ShoppingDaoImplAdapter implements ShoppingDaoPort {
        Random random = new Random();

    @Override
    public long saveOrder(String productName) {
        long orderId = random.nextLong();
        return orderId;
    }

    @Override
    public void updateInventory() {
        // Update inventory table per the database implementation
    }

}

We just saw that ShoppingServiceAdapter uses ShoppingDaoPort to access ShoppingDaoImplAdapter. In future, other adapter can replace ShoppingDaoImplAdapter to switch between such implementations. We achieved this loose coupling in this n-tier architecture through ports, or say interfaces.Presentation (User Interface) & Framework (JMS, Database etc) depends on Services & Dao layer to exchange outside data with the application. And we can see in the pictorial representation that Service & Dao APIs shield the core to control what goes in & out of the system. For instance, the outermost layer controller ShoppingControllerAdapter is again an adapter accessing the port ShoppingServicePort of the next layer.


    @Controller
    public class ShoppingControllerAdapter {
    @Autowired
    ShoppingServicePort shoppingServicePort;

    @GetMapping(path = "/")
    public String welcome(Model model) {
        return "orderForm";
    }

    @PostMapping(path = "/order")
    public String triggerOrder(HttpServletRequest request) {
        try {
            Order order = shoppingServicePort.placeOrder(request.getParameter("productName"));
            shoppingServicePort.updateInventory();
            return "orderSuccess";
        } catch (Exception e) {
            logger.error("Order Failed");
            return "orderFail";
        }
    }
}

Simply put, ports are the integration points so that one adapter can access other adapters indirectly. This means loose coupling. For example, presentation adapter uses service port to call service adapter. Further, service adapter uses Dao port to access read/write/update operations in Dao adapter.We must keep in mind that database is not an inner entity as it may seem. It is still an outside entity which is driven only by business APIs to decide what is stored and returned. The same applies for any interaction over HTTP, messaging, cloud access, web services etc. All of these are removable adapter resources outside the application interacting through respective ports.3. ConclusionLet's conclude with the benefits of this architecture. There are many good reasons to choose hexagonal architecture for complex applications. Let's outline the important ones.In a hexagonal architecture system, we focus on development of one layer at a time, be it core business layer or any other.It is easy for us to test core layer independently by mocking outer entities.Applications are easy to maintain as change in an adapter is abstract for adapters in other layers due to loose coupling.Applications become scalable. That means we can replace and add adapters, as and when required.
