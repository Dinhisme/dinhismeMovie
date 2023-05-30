<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid main-edition">
	<div class="col-sm-8 col-sm-offset-2 video-edition">
		<ul class="nav nav-tabs">
			<li class="${active1}"><a data-toggle="tab" href="#menu1">USER
					EDITION</a></li>
			<li class="${active2}"><a data-toggle="tab" href="#menu2">USER
					LIST</a></li>
		</ul>
		<div class="tab-content">
			<div id="menu1"
				class="tab-pane fade in ${active1} row video-edition-content">
				<form action="./UserEditorServlet" method="post">
					<div class="col-sm-10 col-sm-offset-1" style="color: black;">
						<div class="row container-fluid">
							<div class="ip-su col-lg-6">
								<label for="fn">
									<h4>User Fullname</h4>
								</label> <input type="text" id="fn" class="form-control"
									placeholder="User Fullname" name="fullname"
									value="${userEdit.fullname}" required />
							</div>
							<div class="ip-su col-lg-6">
								<label for="us">
									<h4>User Username</h4>
								</label> <input type="text" id="us" placeholder="User Username"
									class="form-control btn-changePW" readonly
									value="${userEdit.id}" name="id" />
							</div>
						</div>
						<div class="row container-fluid">
							<div class="ip-su col-lg-6">
								<label for="em">
									<h4>User Email</h4>
								</label> <input type="email" id="em" class="form-control"
									placeholder="User Email" name="email" value="${userEdit.email}"
									required />
							</div>
							<div class="ip-su col-lg-6">
								<label for="opw">
									<h4>User password</h4>
								</label> <input type="password" id="opw" class="form-control"
									placeholder="User Password" name="password"
									value="${userEdit.password}" required />
							</div>
						</div>
						<div class="row container-fluid">
							<div class="ip-su col-lg-6">
								<label for="">
									<h4>User Role</h4>
								</label>
								<div class="rdo">
									<div class="">
										<input type="radio" value="true" name="admin" id="Admin"
											${userEdit.admin? 'checked':''} /> <label for="Admin">Admin</label>
									</div>
									<div class="">
										<input type="radio" value="false" name="admin" id="User"
											${userEdit.admin? '':'checked'} /> <label for="User">User</label>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="text-right button-edition"
								style="margin-bottom: 20px;">
								<button class="btn" formaction="./UserEditorServlet?action=update">Update</button>
								<button class="btn" formaction="./UserEditorServlet?action=delete">Delete</button>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div id="menu2" class="tab-pane fade menu2 in ${active2}">
				<table class="table">
					<thead>
						<tr>
							<th>Username</th>
							<th>Fullname</th>
							<th>Password</th>
							<th>Email</th>
							<th>Role</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listUsers}">
							<tr>
								<td data-label="Username">${user.id}</td>
								<td data-label="Fullname">${user.fullname}</td>
								<td data-label="Password">${user.password}</td>
								<td data-label="Email">${user.email}</td>
								<td data-label="Role">${user.admin? 'Admin':'User'}</td>
								<td data-label="Edit"><a
									href="./UserEditorServlet?action=edit&userID=${user.id}"
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