<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid main-edition">
	<div class="col-sm-10 col-sm-offset-1 video-edition">
		<ul class="nav nav-tabs">
			<li class="${active1}"><a data-toggle="tab" href="#menu1">
					FAVORITES</a></li>
			<li class="${active2}"><a data-toggle="tab" href="#menu2">FAVORITES
					USERS</a></li>
			<li class="${active3}"><a data-toggle="tab" href="#menu3">SHARED
					FRIENDS</a></li>
		</ul>
		<div class="tab-content">
			<div id="menu1" class="tab-pane fade in menu1-f ${active1}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Video Title</th>
							<th>Farvorite Count</th>
							<th>Latest Date</th>
							<th>Oldest Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vdStatistical" items="${videosProcStatistical}">
							<tr>
								<td class="video-mini-farvorite">
									<div class="img-video-minis">
										<img src="views/thumbnail/${vdStatistical[0]}" alt="">
									</div>
								</td>
								<td data-label="Video Title">${vdStatistical[1]}</td>
								<td data-label="Farvorite Count">${vdStatistical[2]}</td>
								<td data-label="Latest Date">${vdStatistical[3]}</td>
								<td data-label="Oldest Date">${vdStatistical[4]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-lg-12 text-center button-arrow-video-edition">
					<button class="btn glyphicon glyphicon-chevron-left"></button>

					<button class="btn glyphicon glyphicon-arrow-left"></button>

					<button class="btn glyphicon glyphicon-arrow-right"></button>

					<button class="btn glyphicon glyphicon-chevron-right"></button>
				</div>
			</div>
			<div id="menu2" class="tab-pane fade menu2-f in ${active2}">
				<div class="farvorite-users">
					<div class="col-sm-8 col-sm-offset-2 farvorite-search">
						<label class="col-sm-2 text-right" for="videoTitile">
							<h4>Video Title</h4>
						</label>
						<form action="./ReportServlet?page=favorite_editor&action=tab2"
							method="post">
							<div class="ip-edition col-sm-8">
								<select class="form-control" name="titleVdTab2"
									style="border: 2px solid black; border-radius: 15px;">
									<c:forEach var="vdStatistical" items="${videosProcStatistical}">
										<option value="${vdStatistical[1]}">${vdStatistical[1]}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-1">
								<button class="btn"
									style="border: 2px solid black; border-radius: 25px">Search</button>
							</div>
						</form>
					</div>
				</div>
				<div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Username</th>
								<th>Fullname</th>
								<th>Email</th>
								<th>Farvorite Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fVideo" items="${videosProcFilterVideo}">
								<tr>
									<td data-label="Username">${fVideo[0]}</td>
									<td data-label="Fullname">${fVideo[1]}</td>
									<td data-label="Email">${fVideo[2]}</td>
									<td data-label="Farvorite Date">${fVideo[3]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="col-lg-12 text-center button-arrow-video-edition">
						<button class="btn glyphicon glyphicon-chevron-left"></button>

						<button class="btn glyphicon glyphicon-arrow-left"></button>

						<button class="btn glyphicon glyphicon-arrow-right"></button>

						<button class="btn glyphicon glyphicon-chevron-right"></button>
					</div>
				</div>
			</div>
			<div id="menu3" class="tab-pane fade menu2 in ${active3}">
				<div class="farvorite-users">
					<div class="col-sm-8 col-sm-offset-2 farvorite-search">
						<label class="col-sm-2 text-right" for="videoTitile">
							<h4>Video Title</h4>
						</label>
						<form action="./ReportServlet?page=favorite_editor&action=tab3"
							method="post">
							<div class="ip-edition col-sm-8">
								<select class="form-control" name="titleVdTab3"
									style="border: 2px solid black; border-radius: 15px;">
									<c:forEach var="vdStatistical" items="${videosProcStatistical}">
										<option value="${vdStatistical[1]}">${vdStatistical[1]}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-1">
								<button class="btn"
									style="border: 2px solid black; border-radius: 25px">Search</button>
							</div>
						</form>
					</div>
				</div>
				<div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Sender Name</th>
								<th>Sender Email</th>
								<th>Receiver Email</th>
								<th>Sent Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="userShare" items="${videosProcShare}">
								<tr>
									<td data-label="Sender Name">${userShare[0]}</td>
									<td data-label="Sender Email">${userShare[1]}</td>
									<td data-label="Receiver Email">${userShare[2]}</td>
									<td data-label="Sent Date">${userShare[3]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="col-lg-12 text-center button-arrow-video-edition">
						<button class="btn glyphicon glyphicon-chevron-left"></button>

						<button class="btn glyphicon glyphicon-arrow-left"></button>

						<button class="btn glyphicon glyphicon-arrow-right"></button>

						<button class="btn glyphicon glyphicon-chevron-right"></button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>