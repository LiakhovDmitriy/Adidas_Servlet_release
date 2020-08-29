<%@ page isELIgnored="false" %>
<%@include file="../../source/includes/header/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="login">
		<div class="wrap">
				<div class="col_1_of_login span_1_of_login">
						<h4 class="title">New Customers</h4>
						<h5 class="sub_title">Register Account</h5>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut
								laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation
								ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor
								in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at
								vero eros et accumsan</p>


						<div class="button1">
								<a href="/registration"><input type="submit" name="Submit" value="Continue"></a>
						</div>
						<div class="clear"></div>
				</div>
				<div class="col_1_of_login span_1_of_login">
						<div class="login-title">
								<h4 class="title">Registered Customers</h4>
								<div class="comments-area">

										<c:choose>
												<c:when test="${(login==null && password==null) || error != null}">
														<form action="/login" method="post">
																<p>
																		<label>Login</label>
																		<span>*</span>
																		<input type="text" name="f1" value="${login}" id="login"/>
																</p>
																<p>
																		<label>Password</label>
																		<span>*</span>
																		<input type="password" name="f2" value="${password}" id="password"/>
																</p>
																<p id="login-form-remember">
																		<c:if test="${error != null}">

																				<label>${error}</label><br>

																		</c:if>
																		<label><a href="#">Forget Your Password ? </a></label>

																</p>
																<p>
																		<input type="submit" value="Login">
																</p>
														</form>
												</c:when>
												<c:otherwise>
														<div>
																<br>
																<label>Login: ${login} </label>
														</div>

														<div>
																<br>
																<label>Hello, ${userPojo.name}! </label><br><br>
																<label>Access granted!</label>
														</div>
														<div class="button1" style="margin-top: unset; ">
																<form class="formLogoutS" action="/login" method="post">
																		<a><input value="logout" type="submit" name="logout"/></a>
																</form>
														</div>
												</c:otherwise>
										</c:choose>

										<%--										<c:if test="${error != null}">--%>
										<%--												<form>--%>
										<%--														<div><label>${error}</label></div>--%>
										<%--												</form>--%>
										<%--										</c:if>--%>
								</div>
						</div>
				</div>
				<div class="clear"></div>
		</div>
</div>

<%@include file="../../source/includes/footer/footer.jsp" %>
