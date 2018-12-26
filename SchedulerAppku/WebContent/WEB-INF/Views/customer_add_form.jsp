<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<div class="container">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<h3 class="page-header">Add Customer</h3>
					</div>
				</div>
				<form:form modelAttribute="customer"
					action="${pageContext.request.contextPath}/customer/savecustomer"
					cssClass="form-horizontal" role="form" method="post">

					<div class="form-group form-group-sm">
						<!-- start left column -->
						<div class="col-sm-6" style="margin-left: -5em">
							<div class="form-group row">
								<label for="customerName"
									class="col-sm-4 control-label labelname"><spring:message
										code="lbl.customerName" /></label>
								<div class="col-sm-6">
									<form:input path="customerName" cssClass="form-control" autocomplete="off" />
									<form:errors path="customerName" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>

							<div class="form-group row">
								<label for="address" class="col-sm-4 control-label labelname"><spring:message
										code="lbl.address" /></label>
								<div class="col-sm-6">
									<form:input path="address" cssClass="form-control" autocomplete="off"/>
									<form:errors path="address" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>

							<div class="form-group row">
								<label for="city" class="col-sm-4 control-label labelname"><spring:message
										code="lbl.city" /></label>
								<div class="col-sm-6">
									<form:input path="city" cssClass="form-control" autocomplete="off" />
									<form:errors path="city" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>

							<div class="form-group row">
								<label for="province" class="col-sm-4 control-label labelname"><spring:message
										code="lbl.province" /></label>
								<div class="col-sm-6">
									<form:input path="province" cssClass="form-control" autocomplete="off" />
									<form:errors path="province" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>
						</div>

						<div class="col-sm-6" style="margin-left: -5em">
							<div class="form-group row">
								<label for="country" class="col-sm-4 control-label labelname"><spring:message
										code="lbl.country" /></label>
								<div class="col-sm-6">
									<form:input path="country" cssClass="form-control" autocomplete="off" />
									<form:errors path="country" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>

							<div class="form-group row">
								<label for="faxNumber" class="col-sm-4 control-label labelname"><spring:message
										code="lbl.faxNumber" /></label>
								<div class="col-sm-6">
									<form:input path="faxNumber" cssClass="form-control" autocomplete="off" />
									<form:errors path="faxNumber" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>

							<div class="form-group row">
								<label for="phoneNumber"
									class="col-sm-4 control-label labelname"><spring:message
										code="lbl.phoneNumber" /></label>
								<div class="col-sm-6">
									<form:input path="phoneNumber" cssClass="form-control" autocomplete="off" />
									<form:errors path="phoneNumber" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>

							<div class="form-group row">
								<label for="postalCode" class="col-sm-4 control-label labelname"><spring:message
										code="lbl.postalCode" /></label>
								<div class="col-sm-6">
									<form:input path="postalCode" cssClass="form-control" autocomplete="off" />
									<form:errors path="postalCode" cssClass="alert-danger" />
									<text class="alert-danger">${message}</text>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group row">
						<div class="col-sm-12" style="margin-left: 9em">
							<input type="submit" value="Save Customer"
								class="btn btn-primary" name="btnSubmit" />
						</div>
					</div>
				</form:form>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>