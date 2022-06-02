package com.example.project.bootdata;

import com.example.project.domain.*;
import com.example.project.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;
    private final RoleRepository roleRepository;
    private final PrivilegesRepository privilegesRepository;



    @Override
    public void run(String... args) throws Exception {


        if(userRepository.findAll().size()==0 && postRepository.findAll().size()==0
                && commentRepository.findAll().size()==0 &&
                roleRepository.findAll().size()==0 && privilegesRepository.findAll().size()==0)
        createOther();


    }



    private void createOther(){


        User u1 = User.builder()
                .username("Evi")
                .email("evi@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .build();

        User u2 = User.builder()
                .username("User")
                .email("user@gmail.com")
                .password(passwordEncoder.encode("user"))
                .build();

        User u3 = User.builder()
                .username("Arthur")
                .email("arthur@gmail.com")
                .password(passwordEncoder.encode("arthur"))
                .build();

        User u4 = User.builder()
                .username("William")
                .email("william@gmail.com")
                .password(passwordEncoder.encode("william"))
                .build();

        User u5 = User.builder()
                .username("Yeager")
                .email("yeager@gmail.com")
                .password(passwordEncoder.encode("yeager"))
                .build();

        User u6 = User.builder()
                .username("Satoru")
                .email("satoru@gmail.com")
                .password(passwordEncoder.encode("satoru"))
                .build();
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);

        Comment c1 = Comment.builder()
                .text("To jest moj pierwszy komentarz tutaj")
                .user(u1)
                .build();


        Comment c2 = Comment.builder()
                .text("To jest moj pierwszy komentarz tutaj")
                .user(u2)
                .build();

        Comment c3 = Comment.builder()
                .text("To jest moj drugi komentarz tutaj")
                .user(u1)
                .build();

        Comment c4 = Comment.builder()
                .text("To jest moj trzeci komentarz tutaj")
                .user(u1)
                .build();


        Post g1 = Post.builder()
                .title("Filter similar names in object array JavaScript")
                .description("so here is a problem I've been struggling with for a little bit now. I want to filter a list of objects but the code seems to work only for arrays. " +
                        "I can i reach the same result with objects? Basically what " +
                        "I want is something like: if card.name = jose. then remove jose._R if card.name = jose._R then remove jose.")
                .creator(u1)
                .comments(Collections.singletonList(c3))
                .build();

        postRepository.save(g1);

        Post g2 = Post.builder()
                .title("Can't find file executable in your configured search path for gnc gcc compiler")
                .description("My problem is that code::blocks error message tells me that it can't find file executable in the search path for gnc gcc compiler. " +
                        "Although, I don't know what that means. I can't build it or run in code::blocks. What do I need to do?\n" +
                        "\n")
                .creator(u2)
                .comments(Collections.singletonList(c1))
                .build();

        postRepository.save(g2);
        Post g3 = Post.builder()
                .title("Data is not showing in ReactJS frontend")
                .creator(u3)
                .comments(Arrays.asList(c2,c4))
                .description("I'm a beginner in ReactJS and I have a chatbot that is running in the backend with fastAPI. I'm not able to show the reply not able to show the answer of the robot in frontend.\n" +
                        "\n" +
                        "the code is showing a chat bubble which I'm posting in chat list but it's not able to show the robot reply also for sending my message to the backend robot I have to click on send button which is showing a nearby chat bubble.")
                .build();
        postRepository.save(g3);

        Post g4 = Post.builder()
                .title("Footer overlaps body section when using 100vh")
                .creator(u1)
                .description("I am doing a portfolio project, and I observed this weird overlapping. I want to have 100vh for the \"about-section\" " +
                        "(Hey I am Lau). Is there a way to use 100vh without getting footer overlap?")
                .build();
        postRepository.save(g4);

        Post g5 = Post.builder()
                .title("how can I access my server ' sonarqube.xxx.xxx:9200' without the port number")
                .creator(u4)
                .description("A little history, sonarqube server is hosted on ec2 instance.\n" +
                        "\n" +
                        "Have created an A record to point to the domain name.\n" +
                        "\n" +
                        "I have issues pointing both Ipnumber:port to domain name. Hence, I can only access the server via domain-name:port")
                .build();
        postRepository.save(g5);

        Post g6 = Post.builder()
                .title("HeapSort counting element comparisons")
                .creator(u5)
                .description("i use HeapSort to sort an array of integers. And i want to know the number of element comparisons for an arraylength of 4. " +
                        "Now when i use a global variable it counts 6 for the worstcase. Well i thought HeapSorts worst-case performance is nlog2(n). " +
                        "So it should be 8 right? So when i go to http://www.allisons.org/ll/AlgDS/Sort/Heap/ " +
                        "and insert my array, it also says 6. But what does this faktor of * 0,75 do in this case? Thanks so far")
                .build();
        postRepository.save(g6);

        Post g7 = Post.builder()
                .title("Visualize only some values in x-axis in BarChart (charts_flutter)")
                .creator(u6)
                .description("I'm developing an app to visualize activity data, and in particular i want to implement something like in the image. " +
                        "I want to show the data of an entire month using BarChart, but in the x-axis i want to hide some days, because if all the days are visualized it appears like a mess. " +
                        "Do you know a quick way in which i can do it?")
                .build();
        postRepository.save(g7);

        c1.setPost(g2);
        c2.setPost(g3);
        c4.setPost(g3);
        c3.setPost(g1);

        commentRepository.save(c1);
        commentRepository.save(c2);
        commentRepository.save(c3);
        commentRepository.save(c4);


        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");


        u1.setPosts(Arrays.asList(g1,g2));
        u2.setPosts(Arrays.asList(g3,g4));
        u3.setPosts(Collections.singletonList(g5));
        u4.setPosts(Collections.singletonList(g6));
        u5.setPosts(Collections.singletonList(g7));

        u1.setRole(adminRole);
        u2.setRole(userRole);
        u3.setRole(userRole);
        u4.setRole(userRole);
        u5.setRole(userRole);
        u6.setRole(userRole);
        adminRole.setUsers(Arrays.asList(u1));
        userRole.setUsers(Arrays.asList(u2,u3,u4,u5,u6));
        roleRepository.save(adminRole);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);

    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegesRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegesRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, List<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}

