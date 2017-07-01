function main()
{  
   try {
		if(!json.has("nodeRef") || !json.has("userName") ){
		 exception(404, "Please reverify the input json object.");
		 return;
		} 
	   
	   var nodeRef = json.get("nodeRef");
	   var userName = json.get("userName");
	   logger.info("nodeRef " + nodeRef + " ;UserName" + userName);
	   
	   if((nodeRef == null || nodeRef.trim().length() == 0) || (userName == null || userName.trim().length() == 0) ){
		exception(404, "Request parameter values should not be null or empty");
		return;
	   }
	   
	   var node = search.findNode(nodeRef);
	   logger.info("node : " + node);
	   
	   if (node === null){
		exception(404, "Content is not found in the repository");
		return;
	   }
	   
	   node.setPermission("Read", userName);
	   model.successMsg = "User "+userName+" successfully added as a read permission on content";
	   
   } catch (e){
	   logger.error("Exception occured " + e.message);
	   throw e;
   }

}

function exception(statusCode, statusMsg){
	status.code = statusCode;
	status.message = statusMsg;
}

main();
