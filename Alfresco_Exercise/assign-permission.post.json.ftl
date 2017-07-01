<#escape x as jsonUtils.encodeJSONString(x)>
{
	<#assign statuCode = "${status.code}">
	<#if statuCode == "200">
		"successMsg":"${successMsg}"
	<#else>
		"failureMsg":"${status.message}"
	</#if>
}
</#escape>