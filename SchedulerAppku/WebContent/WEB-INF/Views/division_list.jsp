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
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<h3 class="page-header" style="margin-left: -0.3em">Division List</h3>
					</div>
				</div>
				
				<div class="row">
					<div class="col-12">
						<table class="table table-hover">
							<thead>
								<tr>
									<th style="font-size: 12px;"><spring:message code="lbl.divisionName" /></th>
									<th style="font-size: 12px;">&nbsp;</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="divs" items="${division}">
									<c:url var="updateLink" value="/division/edit">
										<c:param name="divisionId" value="${divs.divisionId}" />
									</c:url>
									<c:url var="deleteLink" value="/division/delete">
										<c:param name="divisionId" value="${divs.divisionId}" />
									</c:url>
									<tr>
										<td style="font-size: 12px;">${divs.divisionName}</td>
										<td style="font-size: 12px;"><a href="${updateLink}" class="btn btn-warning">Edit</a></td>
										<td><a href="${deleteLink}" class="btn btn-danger"
											onclick="if(!(confirm('Are you sure to delete'))) return false">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<a href="<c:url value='/division/add'/>" class="btn btn-primary">Add
							Division</a>
					</div>
				</div>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>

