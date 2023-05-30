<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid main-home">
	<div class="col-sm-10 col-sm-offset-1 grand-video">
		<c:forEach var="videoFv" items="${listFvVideo}">
			<div class=" col-sm-3 col-xs-6 parent-video">
				<div class="video-watch-favorite">
					<div class="img-video-watch">
						<a href="./WatchDetailServlet?page=watch_detail&VideoID=${videoFv.id}">
							<img src="views/thumbnail/${videoFv.thumbnail}" alt="">
						</a>
					</div>
					<div class="titleAndbtn">
						<div class="title-video">
							<label for="">${videoFv.title}</label>
						</div>
						<div class="text-right btn-video-favorite" >
							<a href="./VideoFavoriteServlet?page=video_favorite&unlikeThisVideo=${videoFv.id}"><span class="glyphicon fi fi-ss-social-network"></span></a>
							<a href=""><span class="glyphicon glyphicon-share-alt"></span></a>
						</div>
					</div>
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