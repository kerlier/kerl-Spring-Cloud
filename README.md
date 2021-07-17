# kerl-Spring-Cloud

### springCloud与springBoot的相关依赖
```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.0.RELEASE</version>
    </parent>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.1.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

### springCloud使用nacos作为注册中心
```
1. 添加maven依赖
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    
2. application.yml
spring:
  application:
    name: feign-provider # 这里的springApplicationName比较重要，
                           这里会作为server名在nacos名字上
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848  # 这里是nacos的地址
        namespace: b21c6bb1-be4b-4f99-a0b7-ea2619318fa9  # 这里可以用nacos的namepsace
        
3. 在Application启动类上加上
@EnableDiscoveryClient

```

### springCloud使用feign
```
1. 书写interface的api,作为一个公共的maven依赖，提供者和消费者都需要依赖这个jar包
   api的jar需要依赖
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
   </dependency>


    interface(feignClient代码)
   
    @FeignClient(name = "feign-provider")
    public interface UserService {
    
        @GetMapping("/find/{id}")
        UserDTO findById(@PathVariable Long id);
    
        @PostMapping("/insert")
        void insert(@RequestBody UserDTO userDTO);
    }

    注意： feignClient中的name(feign-provider)需要跟提供者的 
           springApplicationName一致
    
2. 提供者

@RestController
public class UserController implements UserService {

    @Override
    public UserDTO findById(Long id) {
        System.out.println("执行provider findById");
        for (UserDTO user : users) {
            if(Objects.equals(id,user.getId())){
                return user;
            }
        }
        return null;
    }

    @Override
    public void insert(UserDTO userDTO) {
        System.out.println("执行provider insert");
        System.out.println("userDto: " + JSONObject.toJSONString(userDTO));
    }
}


```
