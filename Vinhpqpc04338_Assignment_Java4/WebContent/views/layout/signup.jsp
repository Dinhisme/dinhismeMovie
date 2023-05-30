
<div class="container-fluid main-login">
	<div class="container-fluid signup">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 form-su">
				<form action="./SignUpServlet?page=signup" name="frmSU" method="post">
					<div class="form-su-child">
						<div class="">
							<div class="row container-fluid">
								<div class="col-lg-12">
									<img style="margin: 0 5%;" width="90%"
										src="views/images/thumbDinhisme.png" alt="">
								</div>
							</div>
							<div class="row container-fluid">
								<div class="ip-su col-lg-6">
									<label for="fn">
										<h4>Fullname</h4>
									</label> <input type="text" id="fn" class="form-control"
										placeholder="Your fullname" name="fullname" value="${userSignUp.fullname}" required />
								</div>
								<div class="ip-su col-lg-6">
									<label for="us">
										<h4>Username</h4>
									</label> <input type="text" id="us" class="form-control"
										placeholder="Your username" name="id" value="${userSignUp.id}" required />
								</div>
							</div>
							<div class="row container-fluid">
								<div class="ip-su col-lg-6">
									<label for="em">
										<h4>Email</h4>
									</label> <input type="email" id="em" class="form-control"
										placeholder="Your Email" name="email" value="${userSignUp.email}" required />
								</div>
								<div class="ip-su col-lg-6">
									
								</div>
							</div>
							<div class="row container-fluid">
								<div class="ip-su col-lg-6">
									<label for="pw">
										<h4>Password</h4>
									</label> <input type="password" id="pw" class="form-control"
										placeholder="Your password" name="password" value="${userSignUp.password}" required />
								</div>
								<div class="ip-su col-lg-6">
									<label for="rpw">
										<h4>Repeat password</h4>
									</label> <input type="password" id="rpw" class="form-control"
										placeholder="Repeat your password" name="RePassword"
										required />
										<h4>${messageRepass}</h4>
								</div>
							</div>
							<div class="col-lg-12" style="margin-top: 30px;">
								<div class="col-lg-8 col-lg-offset-2 text-center">
									<button type="submit" class="btn btn-primary btn-lg">Sign
										Up</button>
								</div>
								<div class="col-lg-12 text-center">
									<h5 class="last-form-li">
										Already have an account? <a href="./LogInServlet?page=login" class="register"><span
											class="glyphicon
                                                            glyphicon-share-alt"></span>
											Login </a>
									</h5>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>