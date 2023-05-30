<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container-fluid signup">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 form-su">
			<form action="./AccountServlet?page=account" name="frmAC" method="post">
				<div class="form-su-child">
					<div class="container-fluid">
						<h2 class="title-form">Account</h2>
						<p>
						<h3>Your information at this website</h3>
						</p>
					</div>
					<br>
					<div class="row container-fluid">
						<div class="ip-su col-lg-6">
							<label for="fn">
								<h4>Fullname</h4>
							</label> <input type="text" id="fn" class="form-control"
								value="${User.fullname}" name="txtUpdateUserName" required />
						</div>
						<div class="ip-su col-lg-6">
							<label for="us">
								<h4>Username</h4>
							</label> <input type="text" id="us" value="${User.id}"
								class="form-control btn-changePW" readonly />
						</div>
					</div>
					<div class="row container-fluid">
						<div class="ip-su col-lg-6">
							<label for="em">
								<h4>Email</h4>
							</label> <input type="email" id="em" class="form-control btn-changePW"
								value="${User.email}" readonly />
						</div>
						<div class="ip-su col-lg-6">
							<c:if test="${clickchangePW == null}">
								<label for="opw">
									<h4>Your password</h4>
								</label>
								<a href="./AccountServlet?page=account&changePassword=true"> <input
									type="button" class="form-control btn-changePW text-center"
									value="Change password" />
								</a>
							</c:if>
							<c:if test="${clickchangePW == true}">
								<label for="opw">
									<h4>Your password</h4>
								</label>
								<input type="password" id="opw" class="form-control"
									placeholder="Please type your password to change new password"
									name="txtUserOldPassword" required />
								<h4>${messagePassword}</h4>
							</c:if>
						</div>
					</div>
					<c:if test="${newPass == true}">
						<div class="row container-fluid">
							<div class="ip-su col-lg-6">
								<label for="npw">
									<h4>New password</h4>
								</label> <input type="password" id="npw" class="form-control"
									placeholder="Type your new password"
									name="txtUpdateUserPassword" required />
							</div>
							<div class="ip-su col-lg-6">
								<label for="nrpw">
									<h4>Repeat new password</h4>
								</label> <input type="password" id="nrpw" class="form-control"
									placeholder="Repeat your new password"
									name="txtUpdateUserRePassword" required />
									<h4>${messageReNewPassword}</h4>
							</div>
						</div>
					</c:if>
					<br>
					<div class="row text-center">
						<div class="">
							<button type="submit" class="btn btn-primary btn-lg" formaction="./AccountServlet?page=account&changePassword=true">Save</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>