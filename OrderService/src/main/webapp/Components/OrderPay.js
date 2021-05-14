/**
 *
 */


$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateOrderPayForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidOrderPayIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "OrderPaysAPI",
			type: type,
			data: $("#formOrderPay").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onOrderPaySaveComplete(response.responseText, status);
			}
		});
});

function onOrderPaySaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divOrderPaysGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidOrderPayIDSave").val("");
	$("#formOrderPay")[0].reset();
}


// CLIENT-MODEL================================================================
function validateOrderPayForm()
{
// PAY NAME
if ($("#payMethod").val().trim() == "")
 {
 return "Insert Payment Method.";
 }
// CARD TYPE
if ($("#cardType").val().trim() == "")
 {
 return "Insert Card Type.";
 }
 // CARD NO
if ($("#cardNo").val().trim() == "")
 {
 return "Insert Card No.";
 }
 // SSN
if ($("#SSN").val().trim() == "")
 {
 return "Insert SSN.";
 }
// AMOUNT-------------------------------
if ($("#amount").val().trim() == "")
 {
 return "Insert Amount.";
 }
// is numerical value
var tmpAmount = $("#amount").val().trim();
if (!$.isNumeric(tmpAmount))
 {
 return "Insert a numerical value for Amount.";
 }
// convert to decimal price
 $("#price").val(parseFloat(tmpAmount).toFixed(2));
 
// CARD EXP DATE------------------------
 if ($("#cardExpDate").val().trim() == "")
 {
 return "Insert Card Expire Date.";
 }
return true;
}

