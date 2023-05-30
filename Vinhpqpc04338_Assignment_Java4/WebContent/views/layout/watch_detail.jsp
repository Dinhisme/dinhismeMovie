<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid main-detail">
	<div class="col-sm-12 detail-video">
		<div class="col-sm-8 play-detail">
			<div class="video-watch-detail">
				<div class="img-video-watch">
					<iframe src="https://www.youtube.com/embed/${videoDetail.link}"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						allowfullscreen></iframe>
				</div>
				<div>
					<h3>
						<label for="" class="title-video">${videoDetail.title}</label>
					</h3>
					<p>${videoDetail.description}</p>
				</div>
				<div class="text-right btn-detail-video-favorite">
					<c:if test="${alreadyLike == false}">
						<a
							href="./WatchDetailServlet?likeThisVideo=${videoDetail.id}&VideoID=${videoDetail.id}"
							class="a1"> <span
							class="glyphicon glyphicon-like fi fi-rs-social-network"></span>
							<label>${likes}</label>
						</a>
					</c:if>
					<c:if test="${alreadyLike == true}">
						<a
							href="./WatchDetailServlet?likeThisVideo=${videoDetail.id}&VideoID=${videoDetail.id}"
							class="a1"> <span
							class="glyphicon glyphicon-like fi fi-ss-social-network"></span>
							<label>${likes}</label>
						</a>
					</c:if>
					<a class="a2" data-toggle="modal" id="fpw"
							data-target="#exampleModal"><span
						class="glyphicon glyphicon-share-alt"></span> <label>Share</label></a>
				</div>
				<div class="modal fade fp-dialog" id="exampleModal" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content" style="color: black;">
							<div class="text-center dialog-header">
								<h3>Share</h3>
							</div>
							<form class="modal-body row" name="frmFW" action="./WatchDetailServlet" method="post">
								<div class="ip ip-dialog">
									<h4>Share to your friend email!</h4>
									<div class="ip">
										<input style="color: black;" type="email" class="form-control"
											placeholder="Type your friend email" id="Email" name="emailSend" required />
										<div class="text-center"
											style="margin-top: 20px; margin-bottom: -15px;">
											<button type="submit" class="btn btn-primary btn-dialog" formaction="./WatchDetailServlet?body=https://youtu.be/${videoDetail.link}&action=share&VideoID=${videoDetail.id}">Send</button>
										</div>
									</div>
								</div>
								<br>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<c:forEach var="video" items="${listVideo}">
				<c:if test="${video.id != videoDetail.id}">
					<a
						href="./WatchDetailServlet?&VideoID=${video.id}"
						style="color: black; letter-spacing: 0.05em">
						<div class="video-mini-detail col-sm-4">
							<div class="col-sm-1 img-video-mini ">
								<img src="views/thumbnail/${video.thumbnail}" alt="">
							</div>
							<div class="col-sm-11 title-video-mini" style="margin-top: 6px;">
								<label for="">${video.title}</label>
								<p>${video.description}</p>
							</div>
						</div>
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>