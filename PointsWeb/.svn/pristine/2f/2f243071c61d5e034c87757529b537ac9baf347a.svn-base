/**
 * @author Ayman Yosry
 */
function hashUserData(formID)
{
	var mobileID  = formID + ':user';
	var passwordID  = formID + ':password';
	var hashsum1ID  = formID + ':hashsum1';
	var hashsum2ID  = formID + ':hashsum2';
	var secretKeyID = formID + ':secretkey';
	var hashsumKeyID  = formID + ':hashsumkey';
	
	var password = document.getElementById(passwordID).value;
	var mobile = document.getElementById(mobileID).value;
	var secretKey= document.getElementById(hashsumKeyID).value;
	document.getElementById(secretKeyID).value = secretKey;

	var hashedPassword = hashSHA512(password);
	document.getElementById(passwordID).value = hashedPassword;
	
	var hashsum1 = hashSHA512(hashedPassword+secretKey); 
	document.getElementById(hashsum1ID).value = hashsum1;
	
	var hashsum2 = hashSHA512(hashedPassword+mobile);
	document.getElementById(hashsum2ID).value = hashsum2;
	
	if(formID == "cpf")
	{
		var oldPasswordID  = formID + ':oldpassword';
		var confirmPasswordID  = formID + ':confirmpassword';

		var oldPassword = document.getElementById(oldPasswordID).value;
		var confirmPassword = document.getElementById(confirmPasswordID).value;
		if(confirmPassword != password) return false;
		
		document.getElementById(confirmPasswordID).value = hashedPassword;
		var hashedOldPassword = hashSHA512(oldPassword);
		document.getElementById(oldPasswordID).value = hashedOldPassword;
	}
}

function hashSHA512(password)
{
	var hashedPassword = SHA512(password);
	return hashedPassword;
}

function fromHex(str) 
{ 
    var result = [];
    while (str.length >= 2) 
    { 
        result.push(parseInt(str.substring(0, 2), 16));
        str = str.substring(2, str.length);
    }

    return result;
}

function toHex(arr) 
{
    var result = "";
    for (i in arr) 
    {
        var str = arr[i].toString(16);
        str = str.length == 0 ? "00" :
              str.length == 1 ? "0" + str : 
              str.length == 2 ? str :
              str.substring(str.length-2, str.length);
        result += str;
    }
    return result;
}

function log2(n)
{
	var log = 0;
	if ((n & 0xffff0000) != 0)
	{
		n >>>= 16;
		log = 16;
	}
	if (n >= 256)
	{
		n >>>= 8;
		log += 8;
	}
	if (n >= 16)
	{
		n >>>= 4;
		log += 4;
	}
	if (n >= 4)
	{
		n >>>= 2;
		log += 2;
	}
	return log + (n >>> 1);
}