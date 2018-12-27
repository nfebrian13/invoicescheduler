<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<h2 class="panel-title">Email List</h2>
							</div>

							<div class="panel-body">
								<table class="table table-hover">
									<thead>
										<tr>
											<th style="font-size: 12px;"><spring:message
													code="lbl.sender" /></th>
											<th style="font-size: 12px;"><spring:message
													code="lbl.recipients" /></th>
											<th style="font-size: 12px;"><spring:message
													code="lbl.subject" /></th>
											<th style="font-size: 12px;"><spring:message
													code="lbl.header" /></th>
											<th style="font-size: 12px;"><spring:message
													code="lbl.footer" /></th>
											<th style="font-size: 12px;"><spring:message
													code="lbl.edit" /></th>
											<th style="font-size: 12px;"><spring:message
													code="lbl.delete" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="email" items="${email}">
											<c:url var="updateLink" value="/email/edit">
												<c:param name="emailId" value="${email.emailId}" />
											</c:url>
											<c:url var="deleteLink" value="/email/delete">
												<c:param name="emailId" value="${email.emailId}" />
											</c:url>
											<tr>
												<td style="font-size: 12px;">${email.sender}</td>
												<td style="font-size: 12px;">${email.recipients}</td>
												<td style="font-size: 12px;">${email.subject}</td>
												<td style="font-size: 12px;">${email.header}</td>
												<td style="font-size: 12px;">${email.footer}</td>
												<td><a href="${updateLink}"
													class="btn-custom btn-warning"><span
														class="glyphicon glyphicon-pencil"></span></a></td>
												<td><a href="${deleteLink}"
													class="btn-custom btn-danger"
													onclick="if(!(confirm('Are you sure to delete'))) return false"><span
														class="glyphicon glyphicon-trash"></span></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xl-11 col-lg-11 col-md-11 col-sm-11 col-11">
						<a href="<c:url value='/email/add'/>"
							class="btn-custom btn-primary"><span
							class="glyphicon glyphicon-plus"></span></a>
					</div>
				</div>

			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>