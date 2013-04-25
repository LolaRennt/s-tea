<div class="result">
                <h2>测试结果总结和运行环境</h2>
      			<table class="table table-bordered" style="width: 700px; margin:auto">
                    <thead>
                    <tr style="background-color: #00b3ee">
                        <th>运行系统</th>
                        <th>运行结束时间</th>
                        <th>运行浏览器</th>
                        <th>浏览器版本</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr style="background-color: #ffffff">
                        <td>${env.OS}</td>
                        <td>${des.time}</td>
                        <td>${env.browser}</td>
                        <td>${env.browserVersion}</td>
                    </tr>
                    </tbody>
                </table>
</div>
<div class="itable">
                <h2>测试用例>></h2>
                <table class="table table-bordered" style="width: 900px;margin:auto">
                    <tbody>
                    <tr style="background-color: #00b3ee">
                        <th colspan="2" style="height: 100px">测试用例执行汇总</th>
                    </tr>
                    <#if des.failureCase>
                    <tr class="wrong">
                    <#else>
                    <tr class="right">
                    </#if>
                        <th style="width: 150px">测试用例方法名</th>
                        <td>${des.methodName}</td>
                    </tr>
                    <tr class="info">
                        <th>所属类名称</th>
                        <td>${des.testClass.name}</td>
                    </tr>
                    <#if des.failureCase>
                    <tr class="info">
                        <th>异常情况</th> 
                        <td>有异常</td>
                    </tr>
                    <tr class="info">
                        <th>异常信息</th>
                        <td>${des.failure.message}</td>
                    </tr>
                    <#else>
                    <tr class="info">
                        <th>异常情况</th> 
                        <td>无异常</td>
                    </tr>
                    <tr class="info">
                        <th>异常信息</th>
                        <td>无异常信息</td>
                    </tr>
                    </#if>
                    <tr class="info">
                        <th>执行日志信息</th>
                        <td style="text-align: left;font-size:10px"><p><#include "${des.methodName}.inc"></p></td>
                    </tr>
                    </tbody>

                </table>
            </div>