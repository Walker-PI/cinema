// $(document).ready(function() {
//
//     $('.footable').footable();
//
//     searchAll();
//
//     $(document).on("click","#searchButton",function(){
//         var name=$('#user-name-search-input').val();
//         var type=$('#status:selected').val();
//         postRequest(
//             'role/getByName?username='+name,
//             {},
//             function(res){
//                 alert(JSON.stringify(res));
//                 var data=res.content;
//                 var realcontent=data.filter(function (item) {
//                     return item.userType==type;
//                 });
//                 renderUserList(realcontent);
//             },
//             function(err){
//                 alert(JSON.stringify(err));
//                 alert('cuowu');
//             },
//         );
//     });
//
//     $('#changeButton').click(function(){
//         var namelist=[];
//         $('.user_tr td:first-child').each(function(){
//             namelist.push($(this).text());
//         });
//         var typelist=[];
//
//         $('.user_type_change').each(function(){
//             namelist.push($(this).val());
//         });
//         postRequest(
//             'account/changeType',
//             namelist,typelist,
//             function(){
//                 alert('修改成功');
//             },
//             function(){
//             alert('修改失败');
//             },
//         );
//     });
//
//     function searchByNameAndType(){
//
//         postRequest(
//             'role/get/all',
//             {},
//             function(res){
//                 renderUserList(res.content);
//             },
//             function(err){
//                 alert(err);
//                 alert('cuowu');
//             },
//         );
//     }
//
//     function searchByNameAndType(){
//         var name=$('#user-name-search-input').val();
//         var type=$('#status:selected').val();
//         postRequest(
//             'role/getByName?username='+name,
//             {},
//             function(res){
//                 alert(JSON.stringify(res));
//                 var data=res.content;
//                 var realcontent=data.filter(function (item) {
//                     return item.userType==type;
//                 });
//                 renderUserList(realcontent);
//             },
//             function(err){
//                 alert(JSON.stringify(err));
//                 alert('cuowu');
//             },
//     );
//     }
//
//
//     function renderUserList(con){
//         $('#user-list-tbody').html("");
//         con.foreach(function(item){
//             $('#user-list-tbody').append(
//                 '<tr class="user_tr">'+
//                 '<td>'+
//                 item.name+
//                 '</td>'+
//
//                 '<td class="text-right">'+
//                 '<div class="col-sm-4">'+
//                 '<div class="form-group">'+
//                 '<select name="status"  class="form-control user_type_change">'+
//                 '<option value="1" >经理</option>'+
//                 '<option value="2">员工</option>'+
//                 '<option value="3">用户</option>'+
//
//                 '</select>'+
//                 '</div>'+
//                 '</div>'+
//                 '</td>'+
//                 '</tr>'
//
//             );
//         });
//
//     }
// });
