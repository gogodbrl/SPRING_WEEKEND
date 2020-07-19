# SPRING_WEEKEND
SPRING 주말반 개인 정리

2020.06.20 ~ 2020.07.25

강사님 블로그 : http://blog.daum.net/celab

# Spring 공부 정리
- README적어놓고 안볼까봐 여기다 그냥 정리함.
- 참고로 두서없을 수 있음 (선생님 + 구글링 + Spring책 짬뽕이라...)
- 그리고 틀린정보일수도 있음


# 20200620_첫번째수업

## maven이 읽어들이는 폴더 구조
```
src
	main
		- java
			- (패키지명 <스프링에서는 무조건 패키지 사용하도록 강제화>)
		- resource
			- (소스아닌 모든 것들)
		- webapp
			- WEB-INF
				- (HTML, JSP파일)
				- web.xml
	test
		- java
		- resource
```

### 1. Controller Mapper를 사용하여 Controller를 제어한다.
```
	1. ControllerConfig 를 config로 설정한다.
	2. Controller를 작성한다.
	3. 그 Controller를 사용하기 위해서 >> ControllerConfig에서 @Bean을 등록한다.
	4. 사용가능해진다.	
```

Controller는 다음과 같이 작성하게 된다.

```
@Controller
public class AController {
	/** 얘를 객체로 만들어야 되는데, 그걸 관리하는 객체가 bean이다. **/
	@RequestMapping(value="aaa", method=RequestMethod.GET)
	public String Aprocessing() {
		System.out.println("A Processing"); // 처리하는 일
		return "sayhello"; // 뷰지정
	}
}
```

### 2. 기본 요청 서블릿을 Spring 서블릿(DispatcherServlet)을 사용하겠다고 설정하는 방법

(spring-webmvc-5.0.2.RELEASE> DispatcherServlet.class 를 참조)

Webapp > WEB_INF > Web.xml에 다음과 같이 적어준다.

이 설정이 없으면 spring이 안 돌고 톰켓(웹서버) Servlet이 기본으로 돌아가니 반드시 수동으로 입력이 필요!!

```
<servlet>
	<servlet-name>dispatcher</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>	
	</init-param>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
		config.MvcConfig
		config.ControllerConfig
		</param-value>	
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
	<servlet-name>dispatcher</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>

```

### 3. ViewResolver 사용하기

> JSP 파일을 만들고 
> 이걸 mapping을 시키기 위해서 Spring에서 다음과 같이 등록한다.

1. WebMvcConfigurer 인터페이스 implements

2. @Configuration와 @EnableWebMvc 어노테이션 추가

3. configureViewResolvers 메소드 오버라이드

4. 아래처럼 작성

- /WEB-INF/view까지 경로를 무시하고 return할 때 jsp확장자를 무시한다.
```
@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
	registry.jsp("/WEB-INF/view/",".jsp");
}
```

### 4. 웹서버에서 WEB-INF안에 있는 파일은 보안때문에 처리거부당한다.

[호출이 안됨] 

> Dynamic Web Project를 만든다.

> view를 작성하려면 WebContent 안에 작성하게 되는데

> 웹을 사용하면서 WebContent > WEB-INF > view > test.html 식으로 작성하고

> 웹서버 톰켓을 띄우고

> http://127.0.0.1:8080/test/WEB-INF/view/test.html 식으로 작성을 하면 

> 호출이 안된다(404에러발생)

[호출이 됨]

> WebContent 아래 test.html 식으로 작성을 하고

> http://127.0.0.1:8080/test/test.html 식으로 작성을 하면

> 호출이 된다.



# 20200620_두번째수업

## Controller에서 사용하는 Return은 크게 두개다.

1. String ( jsp파일 )
2. ModelAndView
 - Model이라는 건 Data를 의미한다. 즉, Data와 View라고 생각할 수 있다. 즉, View에 데이터를 보낼 때 사용하는 거다.

## SpringBoot와 Spring의 View 호출방법 차이
- SpringBoot같은 경우는 Controller만 만들어서 사용하면 된다.
- Spring은 View를 맵핑하기 위해서 Bean을 생성해서 맵핑해야 한다.
- Spring에서는 Bean을 등록해야 한다 (스프링에게 관리해달라고 요청하는 객체를 Bean이라고 한다.) 

1. 우선 이렇게 Controller를 지정한다.
```
@Controller
public 
AController {
	@RequestMapping(value="aaa", method=RequestMethod.GET)
	public String Aprocessing() {
		return "sayhello"; // 뷰지정
	}
}
```
2. View도 등록해준다. (sayhello.jsp)
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        Hello !!!!!!!!!!!!!!!!
</body>
</html>
```
3. 마지막으로 Bean을 등록해주어야 한다. (ControllerConfig)
    모든 메소드마다 Bean을 생성하며 실제로는 싱글톤으로 객체가 생성된다.
```
@Configuration
public class ControllerConfig {
	@Bean
	public AController aController() {
		return new AController();
	}
}
```
그래야 정상적으로 View를 인식하는 것 같다.


##### ControllerConfig에 @Bean 어노테이션은 Class단위로 작성되어야 한다
> 메소드 단위인줄 알았는데 class 단위다!!!!!!!!!!!!!!!!

#### 근데 @Autowire도 싱글톤인가??
> 그건 아닌듯하다. 구글신 검색이 더필요함.

## view => java 데이터 보내는 방법 [고객 => 서버에게 데이터 보냄]
#### 1. Servlet의 HttpServletRequest 사용하기 ( Web의 기본적인 방법 )

우선 board.jsp에 action을 write_board로 작성함
```
<title>Insert title here</title>
</head>
<body>
 게시물 등록
 <form action="write_board" method="post">
 제목 : <input type="text" name="title"/><br>
 내용 : <textarea rows="20" cols="20" name="contents"></textarea> <br>
 <input type="submit" value="등록" />
 </form>
</body>
</html>
```
[중요]
> /write_board 라고 쓸 경우 : 127.0.0.1:8190/write_board

> write_board 라고 쓸 경우 : 127.0.0.1.8190/infoslab/write_board


다음으로 Controller에서 HttpServletRequest 요청한다.
매개변수에 HttpServletRequest를 쓰면 DispatcherServlet에서 읽어서 이 메소드로 넣어준다.
이렇게 쓰면 POST로 담긴 값을 getParameter로 가져올 수 있다.
```
@RequestMapping(value = "/write_board", method=RequestMethod.POST)
public String 게시물등록하다(HttpServletRequest request) {
	String 제목 = request.getParameter("title");
	String 내용 = request.getParameter("content");
	return "board";
}
```
#### 2. Spring에서 쓰는 Data Binding 방법
- 이것을 많이 사용한다.
- 스프링에서 자동으로 객체를 만들어서 매개변수로 던져준다.

1. board.jsp form에 action을 write_board로 작성하는 것은 동일하다.
```
<title>Insert title here</title>
</head>
<body>
 게시물 등록
 <form action="write_board" method="post">
 제목 : <input type="text" name="title"/><br>
 내용 : <textarea rows="20" cols="20" name="contents"></textarea> <br>
 <input type="submit" value="등록" />
 </form>
</body>
</html>
```
2. BoardVO를 만든다.
```
class BoardVO {
	String title;
	String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
```
3. Controller에 BoardVO를 매개변수로 등록한다.
```
@RequestMapping(value = "/write_board", method=RequestMethod.GET)
public String 게시물등록하다0(BoardVO board) {
	System.out.println(board.getTitle());
	return "board";
}
```
혹은 @RequestParam을 작성하여 요청으로 들어온 "parameter"를 맵핑해서 사용할 수 있다.
```
@RequestMapping(value = "/write_board", method=RequestMethod.POST)
public String 게시물등록하다1(@RequestParam("title") String title, @RequestParam("content") String content) {
	return "board";
}
```

## java => view 데이터 보내는 방법[서버 => 고객에게 데이터 보냄]

1.  ModelAndView를 사용하면 서버가 View에게 데이터를 전송할 수 있다.
- Object 이하의 모든 객체 다보낼 수 있다.
- setViewName으로 ModelAndView의 데이터를 지정한다.
```
@RequestMapping(value = "/write_board", method=RequestMethod.POST)
public ModelAndView WriterInfo(BoardVO board) {
	ModelAndView data = new ModelAndView();
	//뷰 이름 지정
	data.setViewName("writeinfo");
	//뷰에 보낼 데이터 추가
	data.addObject("title", board.getTitle());
	data.addObject("board", board);
	return data;
}
```

2. return된 data는 view에서 **write_board.jsp**를 찾는다. 
왜냐면 ViewName을 "write_board"로 정해주었고 ViewResolver에서 jsp를 빼주었기 때문이다.

3. jsp에서 받는 방법
- Board라는 객체를 받기위해서 Board클래스를 import 해준다.
- 스크립틀릿에서 request.getAttribute 메소드를 사용하여 받는다.

//writeinfo.jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.stone.infolabs.게시물관리.Board" %>
<%
	String title = (String) request.getAttribute("title");
	Board 게시물 = (Board) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= title %> 글이 잘 저장되었습니다.
	<%= 게시물.getTitle() %> 글이 잘 저장되었습니다.
</body>
</html>
```

즉 서버에서 view로 보낼 때는 request.getAttribute를 이용하고
view에서 보낸 데이터를 서버에서 받을 때는 request.getParameter를 이용한다고 보면된다.

## 스프링 어노테이션
@Configuration 애노테이션이 붙은 설정 클래스를 내부적으로 스프링 빈으로 등록한다.
@Autowired가 붙은 대상에 대해 알맞은 빈을 자동으로 주입한다.
@EnableWebMvc 스프링 MVC 설정을 활성화한다.
@RequestParam 요청된것중에 입력된 것

## Mapper 사용방법 2가지
https://sosohanya.tistory.com/30
 

# 20200620_세번째수업

## Mysql Docker로 설치하기
#### 1. docker starter설치
```
https://hub.docker.com/editions/community/docker-ce-desktop-windows
```

#### 2. cmd에서 docker 설치확인
```
시작 > cmd > cmd창에 docker 입력
```

#### 3. mysql 이미지 불러오기
```
C:\Users\TJ> docker pull mysql
(* 버전을 안적으면 가장 stable 한 mysql을 가져오게 된다.)
```

#### 4. docker 이미지 확인
```
C:\User\TJ> docker images 
```

#### 5. mysql 컨테이너 띄우기
```
C:\Users\TJ> docker run -d               //백그라운드로 띄우겠다.
-p 3306:3306                             //host의 3306포트를 docker의 3306로 맵핑하겠다.
-e MYSQL_ROOT_PASSWORD=1234              //root password는 1234이다.
--name mysql2 mysql:latest               //mysql2라는 이름으로 mysql:latest의 이미지를 맵핑하겠다. 
--character-set-server=utf8mb4           //서버 charset설정
--collection-server=utf8mb4_unicode_ci   //서버 collection 설정
```

#### 6. 프로세스 확인
```
C:\Users\TJ> docker ps -a
```

#### 7. 서버로 이동
```
C:\Users\TJ> docker exec -it mysql2 "bash"
root@366ea4c38e4b:/#
```

#### 8. 데이터베이스 접속하기
```
root@366ea4c38e4b:/# mysql -u root -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 8.0.20 MySQL Community Server - GPL

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

#### 9. 데이터베이스 생성하기
```
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.00 sec)

mysql> create database mydb;
Query OK, 1 row affected (0.21 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mydb               |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.00 sec)
```

#### 10. 유저만들기
```
mysql> create user test;
Query OK, 0 rows affected (0.08 sec)
```

#### 11. 테이블 생성
```
mysql> use mydb;
Database changed
mysql> show tables;
Empty set (0.00 sec)

mysql > create table board 
(
no int primary key auto_increment,
title varchar(20),
contents varchar(50),
writedate timestamp default current_timestamp
);
```

#### 12. 테이블 확인 
```
mysql> show tables;
+----------------+
| Tables_in_mydb |
+----------------+
| board          |
+----------------+
1 row in set (0.00 sec)

mysql> desc board;
+-----------+-------------+------+-----+-------------------+-------------------+
| Field     | Type        | Null | Key | Default           | Extra             |
+-----------+-------------+------+-----+-------------------+-------------------+
| no        | int         | NO   | PRI | NULL              | auto_increment    |
| title     | varchar(20) | YES  |     | NULL              |                   |
| contents  | varchar(50) | YES  |     | NULL              |                   |
| writedate | timestamp   | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
+-----------+-------------+------+-----+-------------------+-------------------+
4 rows in set (0.00 sec)

mysql>
```

#### 13. DB Tool에 붙이기
13-1. 권한생성
```
mysql> mysql -u root -p

mysql> alter user 'test' identified with mysql_native_password by '1234';
Query OK, 0 rows affected (0.08 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.05 sec)

mysql> grant all privileges on *.* to 'test';
Query OK, 0 rows affected (0.13 sec)
```

13-2. Dbeaver 설치 및 사용
```
알아서 설치 하시오............
```

(2020.07.04) board 테이블 다시 작성
```
create table board 
(
	no int primary key auto_increment,
	title varchar(50),
	contents varchar(100),
	writer varchar(20),
	writedate timestamp default current_timestamp,
	views int default 0
)
```

# 20200620_네번째수업

## Spring web.xml에서 config 읽는 법
```
<servlet>
	<servlet-name>dispatcher</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>			
	</init-param>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			config.MvcConfig
			config.ControllerConfig
		</param-value>	
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
```

- config.MvcConfig는 결국 = src/main/java의 config패키지 안에 MvcConfig.class를 의미한다.
- 즉 com.stone.infolabs.boardmanage.config라고 패키지를 만들었다면 아래처럼 되어야 한다. 
```
<param-value>
	com.stone.infolabs.boardmanage.config.MvcConfig
	com.stone.infolabs.boardmanage.config.ControllerConfig
</param-value>	
```
- MvcConfig와 ControllerConfig는 하나의 템플릿이라고 할 수 있다.
- 환경설정을 잡으려고 만들어 놓은 것

## 뷰와 Controller 사이의 접근방법
|컨트롤반환|컨트롤반환 자료형|
|------|---|
|뷰 only|void|
|뷰|String|
|뷰+데이터|ModelAndView|

## void로 Controller를 return하면 어떻게 될까??
- RequestMapping에 있는 bbb를 가져오고 싶음.
```
//컨트롤러에 다음과 같이 지정
@RequestMapping("bbb")
public void question() {
	return ;
}
//bbb.jsp 지정

하면 bbb를 호출한다.
200OK로 바로 인식하는건 스프링에서 해주는 듯 하다.
```

## WEB-INF 아래로 직접요청하면 404에러가 발생한다.
해결방법 : tomcat의 web.xml에 가서 docBase = "infoslab2" pathh="infoslab2" 식으로 변경한다. 
그리고 http://127.0.0.1:8080/infoslab2/jspName 식으로 호출해야한다.
```
//이렇게~!!!!
<Context docBase="SPRING_WEEKEND" path="/infoslab" reloadable="true" source="org.eclipse.jst.jee.server:SPRING_WEEKEND"/></Host>
```


## @Controller를 등록하고 다시 @Bean을 써줘야 하는 이유??
- spring boot에서는 그냥 @Controller만 쓰면 되는거 같은데 Spring에서는 왜 @Controller를 등록하고 다시 @Bean을 등록해야 할까?

**[ 스프링에서의 빈이란? ]**
```
우선 스프링은 경량 컨테이너로서 객체 생성, 소멸과 같은 Life Cycle을 관리하며 스프링 컨테이너로부터 필요한 객체를 얻을 수 있다. 

스프링 컨테이너에 의해서 자바 객체가 만들어지게 되면 이 객체를 스프링은 스프링 빈(Bean)이라고 부른다.

스프링 빈과 자바 일반 객체와의 차이점은 없고 스프링 컨테이너에 의해 만들어진 객체를 스프링 빈이라고 부를 뿐이다.
```

스프링에서 빈을 등록하는 방법이 두가지라고 한다.

#### 1번 -  직접주입(@Bean & @Configuration)
```
초기 스프링 기반 개발에서 빈 생성은 xml로 된 스프링 설정파일을 통해 이루어졌으며 지금 자바 클래스에서 관련 설정을 대신하는 방법을 주로 사용한다. 

물론 필요에 따라 xml설정은 아직도 사용이 가능하다.

설정 클래스는 @Configuration 어노테이션을 클래스 선언부 앞에 추가를 하면 된다. 또한 특정 타입을 리턴하는 메서드를 만들고 @Bean 어노테이션을 붙여주면 자동으로 해당 타입의 빈 객체가 생성된다.
```
@Bean 어노테이션의 주요 내용은 다음과 같다.
```
 > @Configuration 설정된 클래스의 메서드에서 사용가능.
 > 메서드의 리턴 객체가 스프링 빈 객체임을 선언함.
 > 빈의 이름은 기본적으로 메서드 이름이 됨.
 > @Bean(name=”name”) 으로 이름 변경 가능.
 > @Scope 을 통해 객체 생성을 조정할 수 있음.
 > @Component 어노테이션을 통해 @Configuration 없이도 빈 객체를 생성할수도 있음.
 > 빈 객체에 init(), destroy() 등 라이프사이클 메서드를 추가한 다음 @Bean 에서 지정할 수 있음.
```
@Bean에 이름을 지정하는 방법은 다음과 같다.
```
1) 이름을 명시하지 않는경우
@Component: 소문자로 시작하는 클래스이름이 자동으로 사용됨.
@Bean: 소문자로 시작하는 메서드이름이 자동으로 사용됨.

2) 이름을 명시하는 경우
@Component: @Component(“이름”)과 같이 사용.
@Bean: @Bean(name=”이름”)과 같이 사용.
오토와이어링시 이름 사용 @Autowired 에서 특정 이름의 Bean 을 가지고 오려면 @Qualifier 어노테이션을 사용해야 한다.
```
근데 같은 메소드에 대해 두번 선언하면 이런 에러가 난다.
```
@Configuration
public class ControllerConfig {
	@Bean
	public 게시물관리자 준비하다() {
		return new 게시물관리자();
	}
	@Bean
	public 게시물관리자 등록하다() {
		return new 게시물관리자();
	}
}
```
```
심각: 웹 애플리케이션 [/infoslab] 내의 서블릿 [dispatcher]이(가) load() 예외를 발생시켰습니다.
java.lang.IllegalStateException: Ambiguous mapping. Cannot map '등록하다' method 
public java.lang.String com.stone.infolabs.boardmanage.presentation.게시물관리자.준비하다()
to {[/prepare_board],methods=[GET]}: There is already '준비하다' bean method
```
이렇게 쓰면 게시물관리자() 라는 Controller가 뭘 선택해야 할지 해깔려하는걸로 보인다.

하나의 컨트롤러에는 하나의 new를 @Bean에 등록하니까 에러가 안났다.
내 생각에 Controller Class당 하나여야 에러가 발생을 안하는데 문제가 생긴다.
```
// 즉, 이것만 써야 되는거임..
@Configuration
public class ControllerConfig {
	@Bean
	public 게시물관리자 게시물관리자() {
		return new 게시물관리자();
	}
}

```
찾아보니 이걸 **직접 주입 방식** 이라고 한다. new를 개발자가 하는 것이다.
이걸 스프링3과 4에서 썼는데 5에서부터 @Autowire이라는 자동주입방식을 많이 쓴다.

#### 2번 - 자동주입(@Autowire & @Resource & @Repository & @Primary & @Qualifier)

이렇게 의존대상을 설정 코드에서 직접 주입하지 않고 스프링이 자동으로 의존하는 빈 객체를 주입하는 기능이라고 한다.
```
@Autowired : 필드메서드, 세터메서드에 붙이면 메서드 파라미터 타입에 해당하는 빈(Bean) 객체를 찾아 인자로 주입한다.(즉, 자동으로 new를 해주고 스프링 컨테이너에 등록한다는 개념인듯?)
@Repository : 의존성 주입 타켓이되는 class
@Resource : 파라미터 이름에 해당하는 빈(Bean) 객체를 찾아 인자로 주입한다.
```
**@Repository**는 @Autowired에 의존할 때 사용되는걸로 보임
```
<예시>
// Parent.java
@Autowired
Children children;

// Children.java
@Repository
public class Children(){
	(생략)
}
```

그러면 메소드에 @Autowired 거는건 뭐야??
생성자, 메소드, 파라미터, 필드에 @Autowired 를 붙일 수 있다.

```
//AutoWired 어노테이션 코드를 보면 다음과같다.
@Target{ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE}
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
	boolean required() default true;
}
```

//BookService.java
```
@Service
public class BookService {
	private BookRepository bookRepository;
	
	//긍까 이게 BookService메소드가 호출되었을 때 BookRepository를 사용해야 할 경우 주입받아서 쓰겠다는 의미임
	@Autowired
	public BookService(BookRepository bookRepository){
		this.bookRepository = bookRepository;
	}
}
```

//BookRepository.java
```
@Repository
public class BookRepository { ... }
```

##### @configutaion에 @Bean을 쓰는게 주입을 받는다는 의미인듯
new의 개념으로 보는게 아니라 SpringIoC에 @Bean을 만들고(이게 new임) 그 @Bean에 데이터를 넣는다(@Autowired)는 개념인가봄
근데 그 @Autowired할 때 Type이 Unique하면 굳이 @configutaion설정을 안해도 되는거같음


다만 문제가 발생할때는 이 때임.
Interface에서 분리되어 @Autowired를 해야하는 클래스가 두개면 문제가 된다.
```
//FirstBookRepository.java
@Repository
public class FirstBookRepository implements BookRepositoryInterface {
(...)
}

//SecondBookRepository.java
@Repository
public class SecondBookRepository implements BookRepositoryInterface {
(...)
}
```
아래처럼 Interface객체로 받았는데 Interface를 참조하는 @Repository가 두녀석이다. 즉 @Bean이 두개다.

```
//나는 FirstBookRepository를 요청 할 지, SecondBookRepository를 요청 할 지 판단못합니다.
@Service
public class BookService {
	@Autowired
	private BookRepositoryInterface interface;
}
```
이 때 **@Primary**랑 **@Qualifier**를 사용한다.
```
@Primary: @Bean 혹은 @Component에 함께 사용하며 객체 생성의 우선권을 부여.
@Qualifier: @Autowired에 함께 사용하며 Bean 의 이름이 같은 객체를 찾음.
```
즉, @Primary는 중복되는 Interface를 상속받는 클래스들 중에서 한놈이 "너가 대빵해라"라고 선언하는 것이다. 
@Service도 @Autowire시에 FirstBookRepository객체를 찾을 것이다.

```
//FirstBookRepository.java
@Repository
@Primary
public class FirstBookRepository implements BookRepositoryInterface {
(...)
}
```
반면에, @Qualifier는 " **BookService에서 FirstBookRepository를 쓸께**" 라고 선택 하는것이다.
```
@Service
public class BookService {
	@Autowired
	@Qualifier("firstBookRepository")
	private BookRepositoryInterface interface;
	
	public void printBookRepository(){
		System.out.println(bookRepository.getClass());
	}
}
```
참고로 @Qualifier가 @Primary보다 강력하다.

**@Resource**
@Autowired, @Resource,@Inject은 모두 의존관계를 자동으로 연결해주는 기능을 가진 어노테이션이다.

|용도|@Autowired|@Inject|@Resource|
|-|-|-|-|
|범용|스프링전용|자바기본 어노테이션| 자바기본 어노테이션
|연결방식|타입에 맞춰서 자동주입|타입에 맞춰서 자동주입|이름에 맞춰서 자동주입

@Inject와 @Resource는 자바 내장 어노테이션이므로 자바를 쓰는 어느곳이든 지 사용가능하다.
@Autowired의 경우에는 스프링에서 등장한 어노테이션이라 스프링 이외에서는 사용 할 수 없다.

```
Bird 인터페이스를 상속하는 Chicken과 Penguin이라는 클래스가 있다.

//Bird.java
public class Bird{}

//Chicken.java
@Component
public class Chicken implements Bird{
	(...)
}

//Penguin.java
@Component
public class Penguin implements Bird{
	(...)
}
```
**@Component**는 개발자가 직접 컨트롤이 가능한 Class의 경우에 사용한다. Class단위로 자동으로 @Bean을 등록하는 역할인 거 같다.
이제 **타입 자동주입** 과  **이름 자동주입** 의 차이점을 알 수 있다.
```
//Chicken 타입으로 연결됨
@Autowired
private Chicken penguin; 

//Penguin 타입으로 연결됨
@Inject
private Penguin chicken;  

//penguin 타입으로 연결되나, Chicken 클래스를 자료형으로 두었기에 캐스팅이 되지 않아 에러발생됨
@Resource
private Chicken penguin; 

//penguin 타입으로 연결되고, penguin 클래스의 값을 호출함.
@Resource
private Bird penguin; 
```
@Qualifier를 이용해 강제적으로 자동주입가능하다.
아래처럼 쓰면 이름에 상관없이 Chicken 타입으로 연결되는 것을 알 수 있다.
```
@Autowired
@Qualifier("chicken")
private Bird penguin;
```

#### 주의할 점
```
@Qualifier에 지정한 한정자 값을 갖는 bean객체가 존재하지 않으면 Exception이 발생한다
==>bean객체도 만든 후에 qualifier가 되어야 한다.

아무튼 @Qualifier에 해당하는 빈 객체를 찾지 못하기 때문에, 스프링 컨테이너를 생성하는데 실패한다.
```
/**중요 - 스프링 @Autowired 어노테이션 적용시 의존 객체를 찾는 순서 **/

> 타입이 같은 bean 객체를 검색한다. => 1개이면 해당 bean 객체를 사용한다.

> @Qualifier가 명시되어 있는 경우 같은 값을 갖는 bean 객체여야 한다.

> 타입이 같은 bean 객체가 두개 이상이고, @Qualifier가 없는 경우 이름이 같은 빈 객체를 찾는다.

> 찾은경우 그 객체를 사용

> 타입이 같은 bean 객체가 두개 이상이면, @Qualifier로 지정한 bean객체를 찾는다.

> 찾은경우 그 객체를 사용

> 위 경우 모두 해당되지 않으면 컨테이너가 Exception을 발생시킨다.

그러니까 1개만 @Autowire을 쓰면 굳이 Bean을 안써줘도 문제가 안되는데 
2개이상 쓸 경우 Bean을 반드시 명시해줘야 하는거 같다. 그럴려면 Bean을 명시적으로 만들어줘야 하는걸로 보인다.

# 20200705_다섯번째수업

## ResultSet ForwardOnly?
속도때문에 규칙을 가지고 옴.

## DispatcherServlet을 순수 Serlvet으로 만들어보기(흉내만)
#### 서블릿이란?
- 외부에서 요청을 받을 수 있는 클래스
- httpServlet을 상속받은 모든 class들은 서블릿으로 볼 수 있다.
- 모든 **데이터의 처리**는 서블릿에서 하는 것으로 약속을 하였다.
- 그래서 jsp도 내부적으로 HttpServlet을 상속받아서 java로 변환이 된다.
- 내가 httpServlet을 상속받으면 나의 서블릿이 되는거다.
- 즉 DispatcherServlet도 만든사람이 DispatcherServlet이라는 서블릿을 만든거다.
- **@WebServlet("className") 에 설정하거나 web.xml에 설정하는 방법 두가지가 있음**
- 그리고 Servlet은 1회용이다.(요청오면 응답주고 끝이다. 사용자정보를 가지고 있지 않는다.)

## WEB-INF를 가지고있어야 Root가 된다.
그 위에  WebContent든 webapp이든 정해진 이름은 없다.
그냥 DynamicWebContent를 쓰면 WebContent가 만들어진거고, Maven을 쓰면 webapp이 만들어진거임.
aaa/WEB-INF로 써도 상관없음
WAS는 WEB-INF를 루트로 아래 데이터를 싸그리 가져가는거임.

## 포워딩으로 Controller -> View로 데이터 보내기 (MVC)
- ServletA -> ServletB로 옮기면 ServletA는 Request객체를 넘기고 소멸한다.
- HttpRequestServlet의 Body에 담긴 데이터를 가져오는 것을 getAttribute메소드로 한다.
- 응답을 하지 않으면 답을 못받는다. 따라서 포워드는 수만번 가능하지만 더이상 포워딩하지 않는 서블릿을 응답으로 취급한다.

|Server|JSP|
|-|-|
|>>> | getAttribute|
|getParameter | <<<|

## 스프링에서 ModelAndView로 데이터를 넘기는 방법 실습(DispatcherServlet이 실제로 동작하는 방식을 간략히 알 수있다. )
```
[준비물]
- MyController.java
- MyModelAndView.java
- MyServlet.java
- WEB-INF/myview/myview.jsp
```
#### 1. MyServlet에서 @WebServlet("url")을 이용해 url을 /request1로 만든다.
```
@WebServlet("/request1")
public class MyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	MyController myController = new MyController();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		(빈값)
	}
}
```
#### 2. ModelAndView라는 VO를 만든다.
```
public class MyModelAndView {
	public String viewName;
	public String attributeName;
	public Object attributeValue;
}

```
#### 3. myController에서 viewResolver를 선언한다.
- 스프링처럼 @Controller와 @RequestMapping없이(주석임) ModelAndView를 이용해 viewResolver를 구현한다.
```
// 지금은 순수 servlet을 사용하기 때문에 Controller를 주석처리합니다. 
//@Controller
public class MyController {
	//순수 servlet을 사용하기 때문에 이것도 주석처리함
	//@RequestMapping("/request1")
	public MyModelAndView GetRequestOneController(HttpServletRequest request) {
		//실제 이렇게 변환하는걸 @Controller 어노테이션에서 하는 것 같다.
		MyModelAndView myModelAndView = new MyModelAndView();
		myModelAndView.viewName="/WEB-INF/myview/myview.jsp"; //setViewName(); 이게 스프링에서 변환하는 ViewResolver 클래스와 같다.
		myModelAndView.attributeName = "city";  // addObject(attributeName,               );
		myModelAndView.attributeValue= "Seoul"; // addObject(             , attributeValue);
		return myModelAndView;
	}
}
```
#### 4. MyServlet doGet메소드에 다음과 같이 컨트롤러의 ModelAndView값을 맵핑한다. 
- 위에서 MyController에서 요청한 myview.jsp로 {"city":"Seoul"}의 값을 Forwarding한다.
```
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청 매칭을 컨트롤러::myController라고 가정하면 다음과 같이 ModelAndView 객체에 값을 넣는다.
		MyModelAndView modelAndView = myController.GetRequestOneController(request);
		
		//2. View 포워딩을 통해 컨트롤러에서 값을 가져올 수 있도록 한다.
		request.setAttribute(modelAndView.attributeName, modelAndView.attributeValue);
		
		//3. RequestDispatcher를 통해서만 서블릿간에 전달할 수 있다. 결국 DispatcherServlet의 역할이 Forwarding이다. 
		//이런식으로 포워딩 하여 request를 controller로 넘겨서 처리하도록 하는거다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelAndView.viewName);
		dispatcher.forward(request, response);
	}
```
#### 5. myview/myview.jsp를 만들고 MyServlet에서 넘긴 데이터를 받는다.
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String city = (String) request.getAttribute("city");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%= city %>
	</body>
</html>
```
#### 6. 웹서버 실행
```
//URL실행 : http://127.0.0.1:8190/infoslab/request1
Seoul
```
#### 7. 이걸로 알 수 있는 것 (나의 결론)
```
1. @RequestMapping("/url")은 실제로 @WebServlet("/url")과 맵핑된다.
2. 스프링 @Controller 어노테이션안에 HttpServletRequest를 받아서 요청 값을 VO에 담는 행동을 할 것이다.  
3. 스프링 컨트롤러에 @RequestMapping("url")후에 public ModelAndView Url(Board board){ ... } 식으로 바로 Board객체에 값을 받을 수 있는 이유는 스프링이 내부적으로 이렇게 처리해주어서가 아닐까..???
```

## Forward와 Redirect의 차이점
• Forward
```
Web Container 차원에서 페이지 이동만 있다. 
실제로 웹 브라우저는 다른 페이지로 이동했음을 알 수 없다. 
그렇기 때문에, 웹 브라우저에는 최초에 호출한 URL이 표시되고 이동한 페이지의 URL 정보는 볼 수 없다. 
동일한 웹 컨테이너에 있는 페이지로만 이동할 수 있다. 
현재 실행중인 페이지와 Forword에 의해 호출될 페이지는 request와 response 객체를 공유한다.

// 사용법
// request 의 Attribute를 실어나를 수 있다. (예시 : request.setAttribute("test","테스트"); )
RequestDispatcher dispatcher = request.getRequestDispatcher(url);
dispatcher.forward(request, response);
```
• Redirect
```
Web Container는 Redirect 명령이 들어오면 웹 브라우저에게 다른 페이지로 이동하라고 명령을 내린다. 
그러면 웹 브라우저는 URL을 지시된 주소로 바꾸고 그 주소로 이동한다. 다른 웹 컨테이너에 있는 주소로 이동이 가능하다. 
새로운 페이지에서는 request와 response객체가 새롭게 생성된다.

//사용법
// sendRedirect로 파라미터를 넘기는 방법은 get 방식처럼 url?test=테스트 이렇게만 가능
response.sendRedirect(url);
```
알아보고 싶은 것
```
1. @Annotation을 만들어지는 원리 + 작동하는 원리 
2. @ControllerAdvice를 이용한 공통 익셉션 처리 ---> 여덟번째 수업으로 이동
```


# 20200712_일곱번째수업

## DAO사용 시 인터페이스 사용하는 이유
왜 인터페이스를 사용하는가?
Business로직에서 DAO에 뭐가오든지 구조가 안바뀌어도 된다.

Interface 객체 사용, Mock 가짜개체로 사용이 가능하다.
서로 맞닿는 곳을 인터페이스로 추상화한다.

DAO요청 시 다음과 같이 요청해서 쓴다.
```
//BoardService.java ( 인터페이스를 쓰면 고정이다.)
@Service
public class 게시물업무관리자 {
	
	@Autowired
	IBoardDAO boardDAO =new BoardDAO();
	

//BeanConfig.java (Bean만 바꿔주면 된다.)
@Bean
public IBoardDAO BoardDAO() {
	return new 가짜게시물DAO();
}
	
```
## Member 테이블 생성
```
create table member(
	no int primary key auto_increment,
	name varchar(20),
	id varchar(10),
	password varchar(10),
	email varchar(20),
	phone varchar(20)
)

```

## @PostConstruct
 - 일반함수에 생성자의 기능을 하는 것을 의미한다.
```
@PostConstruct 애노테이션은 해당 컴포넌트가 완전히 생성된 후(주입된 후)에 한 번 실행해야할 일들을 코딩한 메소드에 붙이는 것이다.
즉, 해당 Bean이 완전히 생성된 후 무언가 작동하므로 NullPointerException이 일어나지 않는다.
물론 생성자에 붙이는 것은 여지없이 에러가 난다.
@Component
public class BeanTest1 {
    
    @Autowired
    BeanTest3 beanTest3;
    
    public  BeanTest1() { 
        System.out.println("BeanTest1 생성");
    }  
    
    @PostConstruct
    public void hello() {
        beanTest3.hello();
        System.out.println("hello Bean1");
    }
}

@Component
public class BeanTest3 {
    public BeanTest3() {
        System.out.println("Beantest3 생성");
    }
    
    public void hello() {
        System.out.println("hello Bean3");
    }
}
```

## @Order 는 뭘까?
- 문제와는 약간 다르지만 특별한 상황에서 Bean 생성 순서를 결정할 수 있는 방법이 @Order다.
- 간단하게 소개하면 같은 인터페이스를 구현하는 여러 Bean들이 어느 한 객체로 주입될 때 순서를 정할 수 있는 것이다.
```
public interface Person {
    public void eat();
}
 
=========================================
@Component
@Order(2)
public class test implements Person {
    @Override
    public void eat() {
        System.out.println("test");
    }
}
=========================================
@Component
@Order(1)
public class Tistory implements Person {
    @Override
    public void eat() {
        System.out.println("tistory");
    }
}
=========================================
@Component
public class BeanTest1 {
    
    @Autowired
    List<Person> people;
    
    public  BeanTest1() { 
        System.out.println("BeanTest1 생성");
    }  
    
    @PostConstruct
    public void hello() {
        people.stream().forEach(x->x.eat());
    }
}

```
## Dispatcher-servlet.xml로 쓰는 것과 @Configuration에 @Bean의 차이
#### xml에서 사용하기
구글링을 해보면 bean을 등록할 때 main > java > webapp > WEB-INF > config > Dispatcher-servlet.xml을 만들어서 사용하는 걸로 보인다.
그리고 이걸 main > java > webapp > WEB-INF > web.xml에 등록해주면 된다고 한다.
```
< ?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
                           http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd 
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd ">
     
    <mvc:annotation-driven />
 
    <context:component-scan base-package="com.s4c.stg.*">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller" />
    </context:component-scan>
     
    <bean id="objectMapper" class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean" />
    <bean class="org.springframework.web.servlet.view.UrlBasedViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
     
</beans>
```
그리고 web.xml에 등록해준다. dispatcher-servlet.xml을 작성하였더라도 web.xml에 등록을 안해주면 spring은 모른다. 

그러니까 xml을 작성했으면 반드시 Web.xml에 등록을 해줘야 한다. 
```
 <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/WEB-INF/config/dispatcher-servlet.xml</param-value>
    </init-param>
  </servlet>
   
  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>WEB-INF/config/spring/context-*.xml</param-value>
  </context-param>
 
```


#### 코드에서 사용하기
- com.stone.infolabs.boardmanage.config/BeanConfig.java파일에 @Configuration과 @Bean으로 사용될 수 있다.
- 이렇게 코드로도 사용 가능하다는 의미이다.
```
@Configuration
public class BeanConfig {
	@Bean
	public 게시물요청제어자 게시물요청제어자() {
		return new 게시물요청제어자();
	}
	
	@Bean
	public I게시물업무관리자 게시물업무관리자() {
		return new 게시물업무관리자();
	}
	
	@Bean
	public IBoardDAO BoardDAO() {
		return new BoardDAO();
	}
}

```
동일하게 java파일만 있으면 스프링은 모른다. web.xml에 다음과같이 등록한다.
```
<servlet>
	<servlet-name>dispatcher</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>			
	</init-param>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			com.stone.infolabs.boardmanage.config.MvcConfig
			com.stone.infolabs.boardmanage.config.BeanConfig
		</param-value>	
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
	<servlet-name>dispatcher</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

각각의 xml 태그를 설명하면
```
1. <servlet>으로 시작하는 내용이 보이는데, web.xml에 dispatcher라는 서블릿을 추가해주겠다는 것이다.

2. dispatcher-servlet의 서블릿에서만 사용할 수 있는 초기화 파라미터로 (<init-param>) contextConfigurationLocation을 주고 있다.

3. contextConfigurationLocation이라는 것은 contextLoader가 <param-value>의 위치에 있는 설정 파일을 읽어들이겠다는 것이며, 그 파일이 dispatcher라는 서블릿으로 만들겠다는 것이다. 

4. 그리고 아래에는 <context-param> 가 다시 나오고 있는데 설정파일이 dispatcher-servlet 말고도 datasource나 transcation 등도 있을 수 있으므로 그것들을 읽기 위해서 적어주었고, 

5. WEB-INF/config/spring 폴더의 context-로 시작해서 .xml로 끝나는 파일을 읽어들이겠다고 명시하였다. 

6. 즉, context-spring.xml 이나 context-root.xml과 같은 형식의 파일들을 읽겠다는 것이다.

7. <listener>내용은 Spring에서 어떤 이벤트를 받기 위해서 대기하는 객체라고 이해하면 된다.
```

## Mysql Xml 연동하기

이렇게 등록을 해보자. 쿼리가 등록되는 xml경로를 설정해주는거다. 물론 @Bean으로 등록도 해줘야함.
```
public class DatabaseConfiguration {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
```


# 20200718 여덟번째 수업

## @ResponseEntity 사용하는 이유
- Return 값을 **html이 아니라** 200 OK, 404 NotFound 등의 코드와 JSON응답을 제공하기 위해 사용함.

#### @ExceptionHandler적용 메소드에서 ResponseEntity로 응답하기
한 메서드에서 정상 응답과 에러 응답을 ResponseBody로 생성하면 코드가 중복될 수 있다.
```
//대충 안좋은 코드라는 의미같음
//회원이 존재하지 않을 때 404 상태를 응답해야하는 기능이 많다면 에러 응답을 위해 ResponseEntity를 생성하는 코드가 중복될 수 있다.
@GetMapping("/api/members/{id}")
public ResponseEntity<Object> member(@PathVariable Long id) {
	Member member = memberDao.selectById(id);
	if(member==null){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}
	return ResponseEntity.ok(member);
}
```
그래서 @ExceptionHandler 애노테이션을 사용하여 공통화시킬 수 있다고 한다.
```
@GetMapping("/api/members/{id}")
public ResponseEntity<Object> member(@PathVariable Long id) {
	Member member = memberDao.selectById(id);
	if(member==null){
		throw new MemberNotFoundException();
	}
	return ResponseEntity.ok(member);
}

//메소드로 분리함
//사용하는 곳에서 new MemberNotFoundException()로 사용
@ExceptionHandler(MemberNotFoundException.class)
public ResponseEntity<ErrorResponse> handleNoData() {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
}
```

## @RestControllerAdvice로 예외처리 공통으로 분리하기
- 에러 처리 코드를 한곳에서 에러 응답을 관리할 수 있다.

```
@RestControllerAdvice("controller")
public class ApiExceptionAdvice {

	//사용하는 곳에서 new MemberNotFoundException()로 사용
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNodata() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No member"))
	}
	
	//사용하는 곳에서 new MethodArgumentNotValidException()로 사용
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleBindException(MethodArgumentNotValidException ex) {
		String errorCodes = ex.getBindingResult()
								.getAllErrors()
								.stream()
								.map(error -> error.getCodes()[0])
								.collect(Collectors.joining(","));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
	}
}
```

## @Profile 사용하기
- 실수로 실서버에 개발서버 IP를 세팅해서 jar나 DB를 실행시키면 참사가 일어난다.
그걸 방지할 방법이 @Profile이다.

```
// 우선 DB에 연결하려면 @Configuration에 @Bean을 설정해주고 @Profile을 Real로 준다.
@Configuration
@Profile("real")
public class RealDBConfig {
	
	@Bean(destoryMethod = "close")
	public DataSource dataSource(){
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdb.Driver");
		(...)
	}
}

// 우선 DB에 연결하려면 @Configuration에 @Bean을 설정해주고 @Profile을 dev로 준다.
@Configuration
@Profile("real")
public class DevDBConfig {
	
	@Bean(destoryMethod = "close")
	public DataSource dataSource(){
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdb.Driver");
		(...)
	}
}

```
그리고 다음과 같이 적용 할 수 있다.
프로필 우선순위는 다음과 같다.
```
setActiveProfiles() -- AnnotationConfigApplicationContext에 context.getEnvironment().setActiveProfiles("dev"); 식으로 등록
자바 시스템 프로퍼티 -- java -jar -Dspring.profiles.active=dev project.jar
OS 환경변수 -- $PATH
```

## JSON 날짜형식 다뤄보기
이거한번 적용해보자
```
@JsonFormat(pattern = "yyyyMMddHHmmss")
private LocalDateTime birthDateTime;
```	

## 트렌젝션 처리

## AOP 프로그래밍 시도해보기
- aspectjweaver에 라이브러리를 사용한다.
- 스프링에서는 spring-aop 모듈과 spring-context 모듈이 의존성에 추가된다.
- Aspect Oriented Programming의 약자이다.(AOP)

#### 사용방법
```
1. Aspect로 사용할 클래스에 @Aspect 애노테이션을 붙인다.
2. @Pointcut 애노테이션으로 공통 기능을 적용할 Pointcut을 정의한다.
3. 공통 기능을 구현한 메서드에 @Around 애노테이션을 적용한다.
```

프록시를 만들어서 쓴다는 데 이게 무슨뜻이지???


## Annotation 만들어보기


