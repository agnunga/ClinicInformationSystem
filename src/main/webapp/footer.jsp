

				<footer>
				<p>© Company 2012</p>
				</footer>
			</div>
		</div>
	</div>

	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>

	<script src="js/index.js"></script>
	<!-- DataTables -->
	<script src="datatables/jquery.dataTables.min.js"></script>
	<script src="datatables/dataTables.bootstrap.min.js"></script>
	<!-- page script -->
	<script>
		$(function() {
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : true,
				"searching" : true,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false
			});
		});
	</script>
</body>
</html>
