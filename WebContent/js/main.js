$(document).ready(function(){
	
	$('.close').click(function(){
		
		$('.alert').addClass('hidden');
	});
	
	
	
	$('.add-to-cart').click(function(event){
		var id= $(this).attr('id');
		var user = $('#username').attr('data-user');
		
		if(user == null)
			$('.alert').removeClass('hidden');
		
		else{
			
			$.get('CartServlet', {
				item_id : id ,
				user_id : user
			}, function(responseText) {
				$('#'+id).text(responseText);
				if(responseText == "Added To Cart")
				$('#'+id).attr('disabled','disabled');
			});
		
		}
	});
	
});
