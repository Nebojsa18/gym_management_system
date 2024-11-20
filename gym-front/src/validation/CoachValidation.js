export default function CoachValidation(coach){
    const errors={}
    console.log("validation");
    console.log(coach);
    const phone_pattern= /^\d{9,}$/;


    if(coach.firstname ===""){
        errors.firstname = "Firstname is required";
    }

    if(coach.firstname.length < 3){
        errors.firstname = "Must be longer than 3 characters";
    }

    if(coach.lastname ===""){
        errors.lastname = "Lastname is required";
    }

    if(coach.lastname.length < 3){
        errors.lastname = "Must be longer than 3 characters";
    }

    if (coach.birthday === "") {
        errors.birthday = "Required";
    } else {
        // Pretvaranje unetog datuma u Date objekat
        const selectedDate = new Date(coach.birthday);
        const today = new Date();

        // Provera da li je uneti datum nakon današnjeg dana
        if (selectedDate >= today) {
            errors.birthday = "Birthday must be before today's date";
        }

        // Provera da li je osoba starija od 16 godina
        const minimumAgeDate = new Date(today.getFullYear() - 16, today.getMonth(), today.getDate());
        if (selectedDate >= minimumAgeDate) {
            errors.birthday = "You must be at least 16 years old";
        }
    }

    if(!phone_pattern.test(coach.phone)){
        errors.phone = "Phone is not valid";
    }

    if(coach.gender === ""){
        errors.gender = "Required";
    }

    if(coach.username===""){
        errors.username = "Required"
    }

    if(coach.username.length < 3){
        errors.username = "Must be longer than 3 characters";
    }

    if(coach.password===""){
        errors.password = "Required"
    }

    if(coach.password.length < 3){
        errors.password = "Must be longer than 3 characters";
    }


    return errors;

}