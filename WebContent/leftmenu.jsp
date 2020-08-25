<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%String projectPath=request.getContextPath(); %>
   <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/main.css" />
						<div id="sidebar">
						<div class="inner">

							<!-- Search -->
								<section id="Search" class="alt">
									<form method="post" action="#">
										<input type="text" name="query" id="query" placeholder="게시물 통합 검색" />
									</form>
								</section>

							<!-- Menu -->
								<nav id="menu">
									<header class="major">
										<h2>CHA life Menu</h2>
									</header>
									<ul>
										<li><a href="<%=request.getContextPath() %>/board/free">자유게시판</a></li>
										<li><a href="<%=request.getContextPath() %>/board/secret">비밀게시판</a></li>
										<li><a href="<%=request.getContextPath() %>/board/ad">홍보게시판</a></li>
										<li><a href="<%=request.getContextPath() %>/board/used">중고장터</a></li>
										<li><a href="<%=request.getContextPath() %>/board/lecture_review">강의후기</a></li>
										<li><a href="<%=request.getContextPath() %>/board/activity">대외활동&공모전</a></li>
										<li><a href="<%=request.getContextPath() %>/board/notice">공지사항</a></li>
										<li>
											<span class="opener">이용안내</span>
											<ul>
												<li><a href="<%=request.getContextPath() %>/board/FAQ">FAQ</a></li>
												<li><a href="<%=request.getContextPath() %>/board/Q&A">Q&A</a></li>
												<li><a href="<%=request.getContextPath() %>/board/suggest">건의사항</a></li>
										</li>
                                            </ul>
									</ul>
								</nav>

							<!-- Section -->
								<section>
									<header class="major">
										<h2>CHA life TV </h2>
									</header>
									<div class="mini-posts">
										<article>
											<a href="https://www.youtube.com/watch?v=gwMa6gpoE9I" class="image"><img src="<%=request.getContextPath() %>/images/pic02.jpg" alt="" /></a>
											<p>방탄소년단 'ON' Kinetic Manifesto Film : Come Prima</p>
										</article>
										<article>
											<a href="https://www.youtube.com/watch?v=dOcHuqNdyZE" class="image"><img src="<%=request.getContextPath() %>/images/pic03.png" alt="" /></a>
											<p>코로나19 뚫고 10년 만에 자유 찾은 벨루가 한 쌍</p>
										</article>
										<article>
											<a href="https://www.youtube.com/watch?v=wnkGZh43R_s" class="image"><img src="<%=request.getContextPath() %>/images/pic04.jpg" alt="" /></a>
											<p>[런닝맨] 9주년 특집 '연도별 시청률 1위 에피소드 모음' 2편</p>
										</article>
									</div>
									<ul class="actions">
										<li><a href="#" class="button">더보기</a></li>
									</ul>
								</section>

							<!-- Section -->
								<section>
									<header class="major">
										<h2>Get in touch</h2>
									</header>
									<p>차의과학대학교 학생 커뮤니티 차라이프를 방문해 주셔서 감사드립니다.</p>
									<ul class="contact">
										<li class="icon solid fa-phone">(031)1899-2010</li>
										<li class="icon solid fa-home">경기도 포천시 해룡로 120<br />
										차의과학대학교</li>
									</ul>
								</section>

							<!-- Footer -->
								<footer id="footer">
									<p class="Homepage"> 홈페이지 가기 :  <a href="https://www.cha.ac.kr/">차의과학대학교</a></p>
								</footer>

						</div>
					</div>
			<script src="<%=request.getContextPath() %>/assets/js/jquery.min.js"></script>
			<script src="<%=request.getContextPath() %>/assets/js/browser.min.js"></script>
			<script src="<%=request.getContextPath() %>/assets/js/breakpoints.min.js"></script>
			<script src="<%=request.getContextPath() %>/assets/js/main.js"></script>