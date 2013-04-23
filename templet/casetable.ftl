<div class="itable">
                <h2>测试用例>></h2>
                <table class="table table-bordered" style="width: 900px;margin:auto">
                    <tbody>
                    <tr class="wrong">
                        <th colspan="2" style="height: 100px">测试用例执行汇总</th>
                    </tr>
                    <#if des.isFailureCase>
                    <tr class="wrong">
                    <#else>
                    <tr class="right">
                    </#if>
                        <th style="width: 150px">测试用例方法名</th>
                        <td>${des.getMethodName}</td>
                    </tr>
                    <tr class="info">
                        <th>所属类名称</th>
                        <td>${des.getTestClass}</td>
                    </tr>
                    <#if des.isFailureCase>
                    <tr class="info">
                        <th>异常情况</th> 
                        <td>有异常</td>
                    </tr>
                    <tr class="info">
                        <th>异常信息</th>
                        <td>${des.getFailure.getMessage}</td>
                    </tr>
                    <#else>
                    <tr class="info">
                        <th>异常情况</th> 
                        <td>无异常</td>
                    </tr>
                    <tr class="info">
                        <th>异常信息</th>
                        <td>${des.getFailure.getMessage}</td>
                    </tr>
                    </#if>
                    <tr class="info">
                        <th>执行日志信息</th>
                        <td><#include "log.txt"></td>
                    </tr>
                    </tbody>

                </table>
            </div>