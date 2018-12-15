<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="lbl.title" /></title>

<style>
.error {
	color: red;
}
</style>

</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>

		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h2 class="page-header">Please Fill Up the form details</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-6">
				<form:form modelAttribute="remarks" action="editremarks"
					cssClass="form-horizontal" role="form" method="post">

					<div class="form-group row" hidden="true">
						<label for="remarksId" class="col-6 col-form-label"><spring:message
								code="lbl.remarksId" /></label>
						<div class="col-6">
							<form:input path="remarksId" cssClass="form-control" />
							<form:errors path="remarksId" cssClass="alert-danger" />
							<text class="alert-danger">${message}</text>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="remarksName" class="col-6 col-form-label"><spring:message
								code="lbl.remarksName" /></label>
						<div class="col-6">
							<form:input path="remarksName" cssClass="form-control" />
							<form:errors path="remarksName" cssClass="alert-danger" />
							<text class="alert-danger">${message}</text>
						</div>
					</div>

					<div class="form-group row">
						<div class="offset-6 col-6">
							<input type="submit" value="Save"
								class="btn btn-primary" name="btnSubmit" />
						</div>
					</div>

				</form:form>
			</div>
		</div>

		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>