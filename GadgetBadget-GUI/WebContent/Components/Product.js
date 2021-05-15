//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidIDSave").val("");
	$("#product")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
// If valid------------------------
   
	var type = ($("#productID").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "productAPI",
		type : type,
		data : $("#product").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		//console.log(response);
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#productGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#productID").val("");
	$("#product")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {

	
	$.ajax({
		url : "productAPI",
		type : "DELETE",
		data : "productID=" + event.target.value,
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
			window.location.reload(true);
			
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#productGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
		
			
			$("#productID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#productCode").val($(this).closest("tr").find('td:eq(1)').text());
			$("#projectName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#projectDesc").val($(this).closest("tr").find('td:eq(2)').text());
			$("#price").val($(this).closest("tr").find('td:eq(2)').text());
				
});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	
	
	// code
	if ($("#productCode").val().trim() == "") {
		return "Please enter code.";
	}
	
	// name
	if ($("#projectName").val().trim() == "") {
		return "Please enter the product name.";
	}

	// desc
	if ($("#projectDesc").val().trim() == "") {
		return "Please enter project description.";
	}
	
	// price
	if ($("#price").val().trim() == "") {
		return "Please enter the price.";
	}
	
	
	
	return true;
}
	
	


	
	