$(document).ready(function(){
    $('${selector}').autocomplete({
        source: function(req, add){
            //pass request to server  
            $.ajax({
                url: '${callbackUrl}',
                type: 'GET',
                cache: false,
                data: req,
                dataType: 'json',
                success: function(json){
                    var suggestions = [];  

                    //process response  
                    $.each(json, function(i, val){  
                        suggestions.push(val.name);  
                    });
                        
                    // call autocomplet callback method with results
                    add(suggestions);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    //alert('error - ' + textStatus);
                    console.log('error', textStatus, errorThrown);
                }
            });            
        }
    });
});