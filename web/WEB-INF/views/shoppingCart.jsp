<%@ page isELIgnored="false" %>
<%@include file="../../source/includes/header/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="../../source/bootstrap/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="../../source/bootstrap/js/bootstrap.js" type="text/javascript">

<c:forEach var="warishee" items="${sessionScope.cartProduct}" varStatus="сounter">
		<div style="margin: 15px">
				<div class="card mb-3">
						<div style="display: flex;">
								<img width="200" height="250" src="${warishee.photoUrlBig1}" alt="Card image cap"
										 style="margin-left: 10%">
								<div class="card-body" style="margin-left: 10%">

										<h5 class="card-title" inline-block>${warishee.name}</h5>
										<p class="card-text" inline-block>${warishee.bigDescription} </p>

										<span class="card-text">$ ${warishee.price} </span>
										<span class="card-text"> Quantity ${warishee.count}</span>
										<br>


										<form action="/cart" method="post">
												<input type="hidden" name="idProduct" value="${warishee.id}"/>
												<input type="text" value="1" name="amount"/>

												<input type="submit" class="buyButton" value="Change quantity"
															 style="outline: none;border: none; background: none; color: black; margin: 5px;"/>
										</form>

										<br>
										<br>
										<form action="/cart" method="post">
												<input type="hidden" name="removeId" value="${warishee.id}"/>
												<input class="cssmenuLogout" style="margin: 0px 0px" type="submit" value="Remove all">
										</form>


								</div>
						</div>
				</div>
		</div>

</c:forEach>

<c:if test="${countCart == 0}">
		<div style="margin: 15px">
				<div class="card mb-3">
						<div style="display: flex;">
								<div class="card-body">
										<h5 align="center">Cart is empty</h5>
								</div>
						</div>
				</div>
		</div>
</c:if>

<%--WORK WORK WORK--%>
<%--<c:forEach var="warishee" items="${cartProduct}" varStatus="сounter">--%>

<%--		<c:if test="${сounter.index == '0'}">--%>
<%--				<div class="box1">--%>
<%--		</c:if>--%>
<%--		<c:if test="${сounter.index == '3'}">--%>
<%--				<div class="box1">--%>
<%--		</c:if>--%>
<%--		<c:if test="${сounter.index == '6'}">--%>
<%--				<div class="box1">--%>
<%--		</c:if>--%>

<%--		<div class="col_1_of_single1 span_1_of_single1">--%>
<%--				<div class="view1 view-fifth1">--%>
<%--						<div class="top_box">--%>
<%--								<h3 class="m_1">${warishee.name}</h3>--%>
<%--								<p class="m_2">${warishee.description}</p>--%>
<%--								<div class="grid_img">--%>
<%--										<div class="css3"><img src="${warishee.photoUrlIcon}" alt=""/></div>--%>
<%--										<div class="mask1">--%>
<%--												<form action="/singleproduct" method="get">--%>
<%--														<input type="hidden" name="idProduct" value="${warishee.id}"/>--%>
<%--														<div class="info"><input type="submit"--%>
<%--																										 style="outline: none;border: none; background: none; color: white; margin: 5px;"--%>
<%--																										 value="Send"/></div>--%>
<%--												</form>--%>
<%--										</div>--%>
<%--								</div>--%>
<%--								<div class="price">$ ${warishee.price}</div>--%>
<%--						</div>--%>
<%--				</div>--%>
<%--				<span class="rating1">--%>
<%--																<c:if test="${warishee.rating == '1'}">--%>
<%--																		<input type="radio" class="rating-input" id="rating-input-1-1"--%>
<%--																					 name="rating-input-1">--%>
<%--																		<label for="rating-input-1-1" class="rating-star"></label>--%>
<%--																</c:if>--%>
<%--																<c:if test="${warishee.rating == '2'}">--%>
<%--																		<input type="radio" class="rating-input" id="rating-input-1-2"--%>
<%--																					 name="rating-input-1">--%>
<%--																		<label for="rating-input-1-2" class="rating-star"></label>--%>
<%--																</c:if>--%>
<%--																<c:if test="${warishee.rating == '3'}">--%>
<%--																		<input type="radio" class="rating-input" id="rating-input-1-3"--%>
<%--																					 name="rating-input-1">--%>
<%--																		<label for="rating-input-1-3" class="rating-star1"></label>--%>
<%--																</c:if>--%>
<%--																<c:if test="${warishee.rating == '4'}">--%>
<%--																		<input type="radio" class="rating-input" id="rating-input-1-4"--%>
<%--																					 name="rating-input-1">--%>
<%--																		<label for="rating-input-1-4" class="rating-star1"></label>--%>
<%--																</c:if>--%>
<%--																<c:if test="${warishee.rating == '5'}">--%>
<%--																		<input type="radio" class="rating-input" id="rating-input-1-5"--%>
<%--																					 name="rating-input-1">--%>
<%--																		<label for="rating-input-1-5" class="rating-star1"></label>--%>
<%--																</c:if>--%>
<%--										        	  (45)--%>
<%--										    	      </span>--%>
<%--				<ul class="list2">--%>
<%--						<li>--%>
<%--								<img src="../../source/images/picEnought/plus.png" alt=""/>--%>
<%--								<ul class="icon1 sub-icon1 profile_img">--%>
<%--										<form action="/cart" method="post" class="button2">--%>
<%--												<input type="hidden" name="idProduct" value="${warishee.id}"/>--%>
<%--												<li><input type="submit" class="button2"--%>
<%--																	 style="outline: none;border: none; background: none; color: white; margin: 5px;"--%>
<%--																	 value="Add To Bag"/>--%>
<%--														<ul class="sub-icon1 list">--%>
<%--																<li><h3>${warishee.description}</h3><a href=""></a></li>--%>
<%--																<li><p>${warishee.shortDescription} <a href="">adipiscing elit,--%>
<%--																		sed--%>
<%--																		diam</a>--%>
<%--																</p></li>--%>
<%--														</ul>--%>
<%--												</li>--%>
<%--										</form>--%>

<%--								</ul>--%>
<%--						</li>--%>
<%--				</ul>--%>
<%--				<div class="clear"></div>--%>
<%--		</div>--%>

<%--		<c:if test="${сounter.index == '2'}">--%>
<%--				<div class="clear"></div>--%>
<%--				</div>--%>
<%--		</c:if>--%>

<%--		<c:if test="${сounter.index == '5'}">--%>
<%--				<div class="clear"></div>--%>
<%--				</div>--%>
<%--		</c:if>--%>
<%--		<c:if test="${сounter.index == '8'}">--%>
<%--				<div class="clear"></div>--%>
<%--				</div>--%>
<%--		</c:if>--%>

<%--</c:forEach>--%>
<%--&lt;%&ndash;										оці два рядка знизу потім перекинуть в іф зверху&ndash;%&gt;--%>
<%--<div class="clear"></div>--%>
<%--</div>--%>

<%@include file="../../source/includes/footer/footer.jsp" %>
