export default function MemberValidation(member){
    const errors={}
    console.log("validation");
    console.log(member);
    const phone_pattern= /^\d{9,}$/;
    const email_pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;



    if(member.firstname ===""){
        errors.firstname = "Firstname is required";
    }

    if(member.firstname.length < 3){
        errors.firstname = "Must be longer than 3 characters";
    }

    if(member.lastname ===""){
        errors.lastname = "Lastname is required";
    }

    if(member.lastname.length < 3){
        errors.lastname = "Must be longer than 3 characters";
    }

    if (member.birthday === "") {
        errors.birthday = "Required";
    } else {
        // Pretvaranje unetog datuma u Date objekat
        const selectedDate = new Date(member.birthday);
        const today = new Date();

        // Provera da li je uneti datum nakon današnjeg dana
        if (selectedDate >= today) {
            errors.birthday = "Birthday must be after today's date";
        }

        // Provera da li je osoba starija od 16 godina
        // const minimumAgeDate = new Date(today.getFullYear() - 16, today.getMonth(), today.getDate());
        // if (selectedDate >= minimumAgeDate) {
        //     errors.birthday = "You must be at least 16 years old";
        // }
    }

    if(!phone_pattern.test(member.phone)){
        errors.phone = "Phone is not valid";
    }

    if(member.gender === ""){
        errors.gender = "Required";
    }

    if(member.email===""){
        errors.email = "Required"
    }
    if(!email_pattern.test(member.email)){
        errors.email= "Email is not valid";
    }

    return errors;

}