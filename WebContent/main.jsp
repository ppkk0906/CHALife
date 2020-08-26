<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String projectPath=request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>차대 커뮤니티 사이트</title>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/main.css" />
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

						<!-- Header -->
							<header id="header">
							<%if(session.getAttribute("userID")==null){ %>
							<form method=post action="<%=projectPath %>/login">
								아이디: <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" required>
								패스워드: <input type="password" name="pw" placeholder="패스워드를 입력하세요" required>
								<input type="submit" value="로그인">
								<input type="button" value="회원가입" onclick="window.location.href='<%=projectPath%>/board/join';"> 
								</form>
								<%}else{ 
								String userID=session.getAttribute("userID").toString();%>
								<%=userID %>님 환영합니다
								<input type=button value="로그아웃" onclick="window.location.href='<%=request.getContextPath()%>/logout';">
								<input type=button value="회원정보 수정" onclick="window.location.href='<%=request.getContextPath()%>/board/user';">
								<%} %>
							</header>

						<!-- Banner -->
							<section id="banner">
								<div class="content">
									<header>
										<h1><br />
										--- CHA life --- for CHA University student</h1>
									</header>
									<p>차 의과학대학교 공식 학생 커뮤니티 '차라이프' 입니다.
										교내 각종 정보를 공지하고 재학생 간 소통 공간을 제공합니다.
										2021학번 신입생 여려분들의 입학을 환영합니다!</p>
									<ul class="actions">
										<li><a href="https://www.cha.ac.kr/" class="button big">공식 홈페이지 보기</a></li>
									</ul>
								</div>
								<span class="image object">
									<img src="images/pic01.jpg" alt="" />
								</span>
							</section>

							<!-- Section -->
								<section>
									<header class="major">
										<h2>이번주 핫이슈</h2>
									</header>
									<div class="features">
										<article>
											<span class="icon fa-gem"></span>
											<div class="content">
												<h3>내일은 미스터트롯</h3>
												<p>종합 편성채널 사상 최고 시청률 35.7% 기록! 2020년 상반기, 5청만 국민을 웃기고 울린 화제의 프로그램</p>
											</div>
										</article>
										<article>
											<span class="icon solid fa-paper-plane"></span>
											<div class="content">
												<h3>킹키부츠</h3>
												<p>다큐멘터리를 기반, 실화를 바탕으로 만들어진 뮤지컬</p>
											</div>
										</article>
										<article>
											<span class="icon solid fa-rocket"></span>
											<div class="content">
												<h3>다만 악에서 구하소서</h3>
												<p>태국에서 충격적인 납치사건이 발생하고
												처절한 암살자 VS 무자비한 추격자
												멈출 수 없는 두 남자의 지독한 추격이 시작된다!</p>
											</div>
										</article>
										<article>
											<span class="icon solid fa-signal"></span>
											<div class="content">
												<h3>오케이 마담</h3>
												<p>난데없는 비행기 납치 사건의 유일한 해결사가 된 부부
												평범했던 과거는 뒤로 하고, 숨겨왔던 내공을 펼치며 인질이 된 승객을 구하기 시작한다!</p>
											</div>
										</article>
									</div>
								</section>

							<!-- Section -->
								<section>
									<header class="major">
										<h2>대학별 홈페이지</h2>
									</header>
									<div class="posts">
										<article>
											<a href="https://www.cha.ac.kr/%EA%B5%90%EC%9C%A1/%EB%8C%80%ED%95%99/%EC%9C%B5%ED%95%A9%EA%B3%BC%ED%95%99%EB%8C%80%ED%95%99/" class="image"><img src="images/pic융.png" alt="" /></a>
											<h3>융합과학대학</h3>
											<p>데이터경영학과 / 의료홍보미디어학과 / 미술치료학과 / 상담심리학과</p>
											<ul class="actions">
												<li><a href="#" class="button">More</a></li>
											</ul>
										</article>
										<article>
											<a href="https://www.cha.ac.kr/%EA%B5%90%EC%9C%A1/%EB%8C%80%ED%95%99/%EA%B1%B4%EA%B0%95%EA%B3%BC%ED%95%99%EB%8C%80%ED%95%99/" class="image"><img src="images/pic건.png" alt="" /></a>
											<h3>건강과학대학</h3>
											<p>보건의료산업학과 / 보건복지행정학과 / 스포츠의학과</p>
											<ul class="actions">
												<li><a href="#" class="button">More</a></li>
											</ul>
										</article>
										<article>
                                            <a href="https://www.cha.ac.kr/%EA%B5%90%EC%9C%A1/%EB%8C%80%ED%95%99/%EC%83%9D%EB%AA%85%EA%B3%BC%ED%95%99%EB%8C%80%ED%95%99/" class="image"><img src="images/pic생.png" alt="" /></a>
											<h3>생명과학대학</h3>
											<p>의생명과학과 / 바이오공학과 / 식품생명공학과</p>
											<ul class="actions">
												<li><a href="#" class="button">More</a></li>
											</ul>
										</article>
										<article>
											<a href="https://nursing.cha.ac.kr/" class="image"><img src="images/pic간.png" alt="" /></a>
											<h3>간호대학</h3>
											<p>간호학과</p>
											<ul class="actions">
												<li><a href="#" class="button">More</a></li>
											</ul>
										</article>
										<article>
											<a href="https://www.cha.ac.kr/%EA%B5%90%EC%9C%A1/%EB%8C%80%ED%95%99/%EC%95%BD%ED%95%99%EB%8C%80%ED%95%99/" class="image"><img src="images/pic약.png" alt="" /></a>
											<h3>약학대학</h3>
											<p>약학과</p>
											<ul class="actions">
												<li><a href="#" class="button">More</a></li>
											</ul>
										</article>

									</div>
								</section>

						</div>
					</div>

				<!-- Sidebar -->
				<jsp:include page="leftmenu.jsp" flush="false" />
</div>
		<!-- Scripts -->
		<!-- 
			<script src="<%//=request.getContextPath() %>/assets/js/jquery.min.js"></script>
			<script src="<%//=request.getContextPath() %>/assets/js/browser.min.js"></script>
			<script src="<%//=request.getContextPath() %>/assets/js/breakpoints.min.js"></script>
			<script src="<%//=request.getContextPath() %>/assets/js/util.js"></script>
			<script src="<%//=request.getContextPath() %>/assets/js/main.js"></script>
			-->

	</body>
</html>