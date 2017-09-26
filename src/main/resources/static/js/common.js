$(document).ready(function() {
	deleteConfirmation();
});

//Delete confirmation
function deleteConfirmation(){
    $('a[data-confirm]').click(function(ev) {
        var href = $(this).attr('href');

        if (!$('#dataConfirmModal').length) {
            $('body').append(
            		`<div id="dataConfirmModal" class="modal fade" role="dialog">
            		  <div class="modal-dialog">
            		    <div class="modal-content">
            		      <div class="modal-header">
            				<h4 class="modal-title">Please confirm</h4>
            		        <button type="button" class="close" data-dismiss="modal">&times;</button>
            		      </div>
            		      <div class="modal-body">
            		        <p>Are you sure?</p>
            		      </div>
            		      <div class="modal-footer">
            		        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            		        <a class="btn btn-success" id="dataConfirmOK">OK</a>
            		      </div>
            		    </div>
            		  </div>
            		</div>`);
        } 
        $('#dataConfirmModal').find('.modal-body').text($(this).attr('data-confirm'));
        $('#dataConfirmOK').attr('href', href);
        $('#dataConfirmModal').modal({show:true});
        
        return false;
    });
}