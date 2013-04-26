<div class="itable">
<div><h2>测试结果</h2></div>
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
                        <#list des as d>
                        	<#if d.failureCase>
                        	<tr class="wrong">
                        	<td></td>
                        	<td><a href="${d.methodName}.html">${d.methodName}</a></td>
                        	<td>${d.testClass.name}</td>
                        	<td>failed</td>
                        	<td><a href="log.html">测试失败了，请查看具体的日志文件</a></td>
                        	<#else>
                        	<tr class="right">
                        	<td></td>
                        	<td><a href="${d.methodName}.html">${d.methodName}</a></td>
                        	<td>${d.testClass.name}</td>
                        	<td>passed</td>
                        	<td>没有异常信息</td>
                        	</#if>
                        	
                        </#list>
                        </tbody>

                    </table>
                </div>