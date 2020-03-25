sample
===
* 注释###

    select #use("cols")# from lb_medicalhistory  where  #use("condition")#

cols
===
	id,patient_id,name,time,hospitalization_id,doctor_id

updateSample
===

	id=#id#,patient_id=#patientId#,name=#name#,time=#time#,hospitalization_id=#hospitalizationId#,doctor_id=#doctorId#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(patientId)){
     and patient_id=#patientId#
    @}
    @if(!isEmpty(name)){
     and name=#name#
    @}
    @if(!isEmpty(time)){
     and time=#time#
    @}
    @if(!isEmpty(hospitalizationId)){
     and hospitalization_id=#hospitalizationId#
    @}
    @if(!isEmpty(doctorId)){
     and doctor_id=#doctorId#
    @}
