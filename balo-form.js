document.getElementById('salesforce-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const form = event.target;
    const formData = new FormData(form);
    
    const firstName = formData.get('first-name').trim();
    const lastName = formData.get('last-name').trim();
    const email = formData.get('email').trim();
    const salesforceExperience = formData.get('salesforce-experience').trim();
    const expertise = formData.getAll('expertise');
    
    if (!firstName || !lastName || !email || !salesforceExperience || expertise.length === 0) {
        alert('Please fill out all required fields.');
        return;
    }
    
    // Create an object to hold form data
    const data = {
        access_key: '556a25c3-e332-4762-98a8-7b473de06b89', // Replace 'YOUR_ACCESS_KEY' with your actual access key
        subject: 'New Salesforce Form Submission',
        from_name: firstName + ' ' + lastName,
        email: email,
        message: `First name: ${formData.get('first-name')}\nLast name: ${formData.get('last-name')}\nPhone: ${formData.get('phone-number')}\nSalesforce Experience: ${salesforceExperience} years\nExpertise: ${expertise.join(', ')}`
    };

    // Submit the form data to Web3Forms
    fetch('https://api.web3forms.com/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        if (result.success) {
            window.location.href = 'https://getbalo.com';
        } else {
            alert('There was an error submitting the form. Please try again.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('There was an error submitting the form. Please try again.');
    });
});
