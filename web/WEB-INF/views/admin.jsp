<%@ page isELIgnored="false" %>
<%@include file="../../source/includes/header/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="register_account">
		<div class="wrap">
				<h4 class="title">Create new PRODUCT</h4>
				<form action="/admin" method="post">


						<div class="col_1_of_2 span_1_of_2">
								Name
								<div><input type="text" name="name"></div>
								Price
								<div><input type="text" name="price"></div>
								Discount
								<div><input type="text" name="discount"></div>
								Select a category
								<div>
										<select id="Category" name="categoryID" class="frm-field required">
												<option value="1">Man</option>
												<option value="2">Woman</option>
												<option value="3">Kids</option>
										</select>
								</div>
								Select a rating
								<div>
										<select id="Rating" name="rating" class="frm-field required">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
										</select>
								</div>
						</div>
						<div class="col_1_of_2 span_1_of_2">
								Short Description
								<div>
										<input type="text" name="shortDescription">
								</div>
								Description
								<div>
										<input type="text" name="description">
								</div>


						</div>
						<div class="formLogoutS" style="margin-top: unset; ">
								<button class="grey" value="send" type="submit">Submit</button>
						</div>

						<p class="terms">By clicking 'Create Account' you agree to the <a href="#">Terms &amp; Conditions</a>.</p>
						<div class="clear"></div>


						<c:forEach var="errorList" items="${ERROR}">
								${errorList}
						</c:forEach>
				</form>
		</div>
</div>

<%@include file="../../source/includes/footer/footer.jsp" %>
