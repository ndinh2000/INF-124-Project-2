function CheckRegistration() {
    var ids = ["D101", "D102", "D103", "D104", "D105", "D106", "D107", "D108", "D109", "D110",
        "C101", "C102", "C103", "C104", "C105", "C106", "C107", "C108", "C109", "C110",];
    var regName = /^[a-zA-Z]+$/;
    var emailCheck = /\S+@\S+\.\S+/;
    var visacardno = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
    var addressCheck = /^\s*\S+(?:\s+\S+){2}/;
    var states = ["AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI",
        "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN",
        "MS", "MO", "MT", "NC", "NE", "NH", "NJ", "NM", "NV", "NY", "ND", "OH",
        "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
        "WV", "WI", "WY"];
    var zipCheck = 	/^(\d{5}(?:\-\d{4})?)$/;

    var fname = document.OrderForm.fname.value;
    var lname = document.OrderForm.lname.value;
    var petID = document.getElementById("product-id").value;
    var qty = document.getElementById("qty").value;
    var email = document.getElementById("clientEmail").value;
    var card = document.getElementById("credit-card").value;
    var expireMM = document.getElementById("expireMM").value;
    var expireYY = document.getElementById("expireYY").value;
    var address = document.getElementById("address").value;
    var state = document.getElementById("state").value.toUpperCase();
    var zip = document.getElementById("zip").value;
    var method = document.getElementById("shipping-method").value;

    console.log('checking')
    console.log(email)
    console.log(expireMM);
    console.log(expireYY);

    if (!regName.test(fname)) {
        alert ("Invalid First Name");
        return (false);
    }
    if (!regName.test(lname)) {
        alert ("Invalid Last Name");
        return (false);
    }
    if (ids.indexOf(petID) === -1) {
        alert ("Invalid Pet ID");
        return false;
    }
    if (!emailCheck.test(email)) {
        alert ("Invalid Email Address");
        return (false);
    }
    // try {
    //     email = new MailAddress(email).Address;
    // } catch(FormatException) {
    //     // address is invalid
    //     alert ("Invalid Email Address");
    //     return (false);
    // }
    if (!visacardno.test(card)) {
        alert ("Invalid Card Number");
        return (false);
    }
    if (expireYY == 21 && expireMM < 4) {
        alert ("Card already expired!");
        return (false);
    }
    console.log('after expireYY')
    if (!addressCheck.test(address)) {
        alert ("Invalid Address");
        return (false);
    }
    console.log('after address')
    if (states.indexOf(state) === -1) {
        alert ("Invalid State");
        return (false);
    }
    console.log('after state')
    if (!zipCheck.test(zip)) {
        alert ("Invalid Zip Code");
        return (false);
    }

    var body = `Hello Pet Shop,%0D%0A%0D%0A`
        + `I'd like to place an order, the details are below:%0D%0A`
        + `Name: ${fname} ${lname}%0D%0A`
        + `Pet ID: ${petID}%0D%0A`
        + `Quantity: ${qty}%0D%0A`
        + `Card Info:%0D%0A`
        + `     Number: ${card}%0D%0A`
        + `     Expirate Date: ${expireMM}/${expireYY}%0D%0A`
        + `Shipping Address:%0D%0A`
        + `     ${address}%0D%0A`
        + `     ${state}, ${zip}%0D%0A`
        + `Shipping Method: ${method}%0D%0A%0D%0A`
        + `Thank you%0D%0A`;
    var link = "mailto:thepetshop@mail.com?subject=Order%20Form&body=" + body;
    window.location.href = link;
    return (false);
}