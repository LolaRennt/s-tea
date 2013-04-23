<div class="itable">
                    <table class="table table-bordered table-hover" style="width: 900px；margin:auto">
                        <thead>
                        <tr style="background-color: #0088cc">
                            <th></th>
                            <th>方法名</th>
                            <th>类名</th>
                            <th>运行</th>
                            <th>运行状况</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list dess as d>
                        	<#if d.isFailureCase>
                        	<tr class="wrong">
                        	<#else>
                        	<tr class="right">
                        	</#if>
                        	<td><a href="case/${d.getMethodName}.html">${d.getMethodName}</a></td>
                        	<td>${d.getTestClass.getName}</td>
                        	<td>passed</td>
                        	<td>${d.getFailure.getMessage}
                        </#list>
                        </tbody>

                    </table>
                </div>