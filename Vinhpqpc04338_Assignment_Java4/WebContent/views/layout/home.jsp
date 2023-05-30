<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="container-fluid main-home">
	<div
		class="col-sm-6 col-sm-offset-6 container-fluid col-xs-10 col-xs-offset-1 grand-input-search">
		<div class="col-sm-7 col-sm-offset-4 parent-input-search">
			<div class="ip-search-home">
				<input type="text" name="" id="" placeholder="Enter Keywords...">
				<div class="button-search-home">
					<button class="glyphicon glyphicon-search"></button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12 row container-fluid detail-video-home">
		<div class="col-sm-8">
			<div class="video-watch-detail-home">
				<div class="img-video-watch">
					<a href="./WatchDetailServlet?page=watch_detail&VideoID=${listVideo[listSize - 4].id}">
						<img src="views/thumbnail/${listVideo[listSize - 4].thumbnail}"
						alt="">
						<div class="poster-video-home">
							<div class="img-poster-video-home">
								<img src="views/poster/${listVideo[listSize - 4].poster}"
									alt="">
							</div>
						</div>
						<div class="btn-poster">
							<button>
								<span class="fi fi-sr-play"></span>
							</button>
						</div>
						<div class="title-video-home">
							<h2>${listVideo[listSize - 4].title}</h2>
							<p>${listVideo[listSize - 4].description}</p>
						</div>
					</a>
				</div>
			</div>

		</div>
		<div class="col-sm-4">
			<c:forEach var="videoTop" items="${listVideo}"
				begin="${listSize - 3}" end="${listSize}">
				<div class="video-mini-detail-home col-sm-4 col-xs-4">
					<div class="col-xs-2 img-video-mini-home">
						<img src="views/poster/${videoTop.poster}" alt="">
					</div>
					<div class="col-xs-2 title-video-mini-home">
						<label for=""> ${videoTop.title}</label>
						<p>${videoTop.description}</p>
					</div>
					<a
						href="./WatchDetailServlet?page=watch_detail&VideoID=${videoTop.id}">
						<div class="col-xs-5 btn-video">
							<button class="btn">
								<span class="glyphicon fi fi-ss-play" style="margin-right: 10px"></span>
								Watch now
							</button>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="col-sm-12 Trending">
		<label for="">Trending</label>
	</div>
	<div class="col-sm-12 text-center grand-video">
		<c:forEach var="video" items="${listVideo}" begin="0"
			end="${listSize - 5}">
			<div class=" col-sm-2 col-xs-4 parent-video">
				<div class="video">
					<div class="img-video">
						<a href="./WatchDetailServlet?page=watch_detail&VideoID=${video.id}">
							<img src="views/poster/${video.poster}" alt="">
						</a>
					</div>
					<div>
						<label for="" class="title-video">${video.title}</label>
					</div>
					<a href="./WatchDetailServlet?page=watch_detail&VideoID=${video.id}">
						<div class="btn-video">
							<button class="btn">
								<span class="glyphicon fi fi-ss-play" style="margin-right: 10px"></span>
								Watch now
							</button>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-xs-12 text-center button-arrow">
		<button class="btn glyphicon glyphicon-chevron-left"></button>

		<button class="btn glyphicon glyphicon-arrow-left"></button>

		<button class="btn glyphicon glyphicon-arrow-right"></button>

		<button class="btn glyphicon glyphicon-chevron-right"></button>
	</div>
</div>