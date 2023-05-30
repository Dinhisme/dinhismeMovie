
<div class="container-fluid main-login">
	<div class="login">
		<div class="row">
			<div class="col-lg-6">
				<img class="img-li" width="60%" src="views/images/logoD.png"
					class="img-fluid">
			</div>
			<div class="col-lg-4 col-lg-offset-1 side-li" id="li">
				<form action="./LogInServlet?page=login" name="frmLi" class="form-li" method="post">
					<div class="row top-li">
						<div>
							<p class="lead top-btn-ic">
								<label> Login with</label>
							</p>
						</div>
						<div>
							<button type="button"
								class="btn btn-primary
                                        btn-ic">
								<img src="views/icon/glyphicon-google.png" alt="">
							</button>
							<button type="button"
								class="btn btn-primary
                                        btn-ic">
								<img src="views/icon/glyphicon-facebook.png" alt="">
							</button>
							<button type="button"
								class="btn btn-primary    
                                        btn-ic">
								<img src="views/icon/glyphicon-twitter.png" alt="">
							</button>
						</div>
					</div>
					<p class="text-center or">
						<label>Or</label>
					</p>
					<div>
						<div class="ip">
							<label for="username">
								<h4>Username</h4>
							</label> <input type="text" id="username" name="txtUsername"
								class="form-control" placeholder="Type username" value="${userNameType}" required>
							<h4>${messageUsername}</h4>
						</div>
						<br>
						<div class="ip">
							<label for="password">
								<h4>Password</h4>
							</label> <input id="password" name="txtPassword" type="password"
								class="form-control" placeholder="Type password" required>
								<h4>${messagePassword}</h4>
						</div>
					</div>
					<br>
					<div class="checkbox-li">
						<div class="chk">
							<input class="chk1" type="checkbox" value="" id="rmbme" /> <label
								class="chk2" for="rmbme">Remember me</label>
						</div>
						<a class="fp-l" data-toggle="modal" id="fpw"
							data-target="#exampleModal"> <label class="chk2" for="fpw">Forgot
								password</label>
						</a>
					</div>
					<div class="text-center row">
						<button type="submit" class="btn btn-primary btn-lg">Login</button>
						<p class="last-form-li">
							Don't have an account? <a href="./SignUpServlet?page=signup" class="register">Sign
								Up</a>
						</p>
					</div>
				</form>
				<div class="modal fade fp-dialog" id="exampleModal" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content" style="color: black;">
							<div class="text-center dialog-header">
								<h3>Forgot Password</h3>
							</div>
							<form class="modal-body row" name="frmFW" action="./LogInServlet" method="post">
								<div class="ip ip-dialog">
									<h4>Please type your email and i'm gonna send you a new password!!!</h4>
									<div class="ip">
										<input style="color: black;" type="email" class="form-control"
											placeholder="Type your email" id="Email" name="emailSend" required />
										<div class="text-center"
											style="margin-top: 20px; margin-bottom: -15px;">
											<button type="submit" class="btn btn-primary btn-dialog" formaction="./LogInServlet?page=login&action=share">Send</button>
										</div>
									</div>
								</div>
								<br>
							</form>
							<div class="container-fluid bottom-dialog">
								<a class="register" href="./LoginServlet?page=login">Login</a> <a
									class="register" href="./SignUpServlet?page=signup"> Sign up</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>