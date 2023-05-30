<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid main-edition">
	<div class="col-sm-10 col-sm-offset-1 video-edition">
		<ul class="nav nav-tabs">
			<li class="${active1}"><a data-toggle="tab" href="#menu1">VIDEO
					EDITION</a></li>
			<li class="${active2}"><a data-toggle="tab" href="#menu2">VIDEO
					LIST</a></li>
		</ul>
		<div class="tab-content">
			<div id="menu1"
				class="tab-pane fade in ${active1} row video-edition-content">
				<form action="./VideoEditorServlet" method="post" enctype="multipart/form-data">
					<div class="col-sm-12">
						<div class="col-sm-12">
							<div class="video-edition-detail col-xs-12">
								<div class="img-video-edition-poster col-xs-2">
									<label for="file-ip-1"> 
									<img id="file-ip-1-preview" src="views/poster/${videoEdit.poster}" alt="">
									</label> 
									<input style="display: none;" type="file" name="img_one" id="file-ip-1" accept="image/*" onchange="showPreview(event, 1);">
								</div>
								<div class="img-video-edition col-xs-10">
									<label for="file-ip-2"> 
										<img id="file-ip-2-preview" src="views/thumbnail/${videoEdit.thumbnail}" alt="">
									</label> 
									<input style="display: none;" type="file" name="img_two" id="file-ip-2" accept="image/*" onchange="showPreview(event, 2);">
								</div>
							</div>
						</div>
						<div class="col-sm-10 col-sm-offset-1">
							<div class="ip">
								<label for="youtubeLink">
									<h4>
										Youtube Link ( Example: https://youtu.be/<span
											style="color: red;">02ODKglDVQs</span> )
									</h4>
								</label> <input type="text" id="youtubeLink" class="form-control"
									placeholder="Type Youtube Link" value="${videoEdit.link}"
									name="link" required>
							</div>
							<br>
							<div class="ip">
								<label for="videoTitile">
									<h4>Video Title</h4>
								</label> <input id="videoTitile" type="text" class="form-control"
									placeholder="Video Title" value="${videoEdit.title}"
									name="title" required>
							</div>
							<br>
							<div class="ip">
								<label for="Description">
									<h4>Description</h4>
								</label>
								<textarea id="Description" rows="5" style="width: 100%; resize: none; border-radius: 10px;" name="description">${videoEdit.description}</textarea>
							</div>
							<div class="checkbox-edition">
								<div class="chk">
									<input class="chk1" type="checkbox" name="active"
										${videoEdit.active? 'checked':''} id="Active" /> <label
										class="chk2" for="Active">Active</label>
								</div>
							</div>
							<div class="row" style="margin-bottom: 15px;">
								<div class="text-right button-edition">
									<button class="btn"
										formaction="./VideoEditorServlet?action=create">Create</button>
									<button class="btn"
										formaction="./VideoEditorServlet?action=update&idUpdate=${videoEdit.id}">Update</button>
									<button class="btn"
										formaction="./VideoEditorServlet?action=delete&idDelete=${videoEdit.id}">Delete</button>
									<button class="btn"
										formaction="./VideoEditorServlet?action=reset">Reset</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div id="menu2" class="tab-pane fade menu1-f in ${active2}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th class="text-center">Youtube Link</th>
							<th>Video Title</th>
							<th>Views Count</th>
							<th>Status</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="video" items="${listVideo}">
							<tr>
								<td class="video-mini-farvorite">
									<div class="img-video-minis">
										<img src="views/thumbnail/${video.thumbnail}" alt="">
									</div>
								</td>
								<td class="text-center" data-label="Youtube Id">${video.link}</td>
								<td data-label="Video Title">${video.title}</td>
								<td data-label="Views Count">${video.views}</td>
								<td data-label="Status">${video.active}</td>
								<td data-label="Edit"><a
									href="./VideoEditorServlet?action=edit&videoID=${video.id}"
									class="edit-btn">Edit</a></td>
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
<script>
    var number = 1;
    do {
        function showPreview(event, number) {
            if (event.target.files.length > 0) {
                let src = URL.createObjectURL(event.target.files[0]);
                let preview = document.getElementById("file-ip-" + number + "-preview");
                preview.src = src;
                preview.style.display = "block";
            }
        }
        function myImgRemove(number) {
            document.getElementById("file-ip-" + number + "-preview").src = "br.png";
            document.getElementById("file-ip-" + number).value = null;
        }
        number++;
    }
    while (number < 5);
</script>