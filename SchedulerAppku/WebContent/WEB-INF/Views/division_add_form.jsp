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
					<div class="col-xl-11 col-lg-11 col-md-11 col-sm-11 col-11"
						style="margin-top: 0.9em;">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h2 class="panel-title">Add Division</h2>
							</div>

							<div class="panel-body">
								<form:form modelAttribute="division"
									action="${pageContext.request.contextPath}/division/savedivision"
									cssClass="form-horizontal" role="form" method="post">

									<div class="form-group">
										<label for="divisionName"
											class="col-sm-2 control-label labelname"><spring:message
												code="lbl.divisionName" /></label>
										<div class="col-sm-4">
											<form:input path="divisionName"
												cssClass="form-control input-sm" autocomplete="off" />
											<form:errors path="divisionName" cssClass="alert-danger-custom" />
											<text class="alert-danger-custom">${message}</text>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12" style="margin-left: 12.4em">
											<input type="submit" value="Save"
												class="btn btn-primary" name="btnSubmit" />
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
