<%@ page contentType="text/html; charset=UTF-8"%>
	<table class="table table-bordered table-condensed" style="background-color:#FFFFFF;">
        <tr ng-repeat="band in bands | orderBy: 'name' | filter:bandSearch">
            <td class="text-center col-xs-9">
            <a href="${pageContext.request.contextPath}/band/detail?bandId={{band.id}}" class="lead" style="text-decoration: none;">
            {{band.name}}</a></td>
            <td class="text-center col-xs-3"><a href="https://twitter.com/{{band.twitter}}"> 
            	<img src="${pageContext.request.contextPath}/img/if_43-twitter_104461.png" height="30%"></a></td>
        </tr>
    </table>