document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }
                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.selectedCategories = [];
            this.selectedCategoriesDescriptions = [];
            this.quantity = 0;
            this.selectedInstitution = 0;
            this.selectedInstitutionName = '';
            this.address = '';
            this.city = '';
            this.postcode = '';
            this.phone = '';
            this.date = '';
            this.time = '';
            this.moreInfo = '';

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    const button = e.target;
                    const step = button.closest('div[data-step]')
                    const stepNo = step.dataset.step;
                    let dataValidated = true;

                    switch (stepNo) {
                        case '1':
                            dataValidated = this.validateStep1(step);
                            break;
                        case '2':
                            dataValidated = this.validateStep2(step);
                            break;
                        case '3':
                            dataValidated = this.validateStep3(step);
                            break;
                        case '4':
                            dataValidated = this.validateStep4(step);
                            break;
                    }
                    if (dataValidated) {
                        this.currentStep++;
                        this.updateForm();
                    }
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => {
                e.preventDefault();
                this.sendDonationData();
                // this.submit(e)
            });
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary
            if (this.currentStep >= 5) {
                document.querySelector('#summary-quantity').textContent = this.quantity + ' worki: ' + this.selectedCategoriesDescriptions.join(', ');
                document.querySelector('#summary-institution').textContent = this.selectedInstitutionName
                document.querySelector('#summary-address').textContent = this.address;
                document.querySelector('#summary-city').textContent = this.city;
                document.querySelector('#summary-postcode').textContent = this.postcode;
                document.querySelector('#summary-phone').textContent = this.phone;
                document.querySelector('#summary-date').textContent = this.date;
                document.querySelector('#summary-time').textContent = this.time;
                document.querySelector('#summary-more-info').textContent = this.moreInfo;
            }
        }

        validateStep1(step) {
            this.selectedCategories.length = 0;
            this.selectedCategoriesDescriptions.length = 0;
            step.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
                if (checkbox.checked == true) {
                    this.selectedCategories.push(checkbox.value);
                    this.selectedCategoriesDescriptions.push(checkbox.parentNode.querySelector('.description').textContent);
                }
            });
            if (this.selectedCategories.length == 0) {
                alert("Nie została zaznaczona żadna kategoria");
            }
            return this.selectedCategories.length > 0;
        }

        validateStep2(step) {
            let result = false;
            let quantityString = step.querySelector('input').value;

            if (quantityString) {
                this.quantity = Number.parseInt(quantityString, 10);
                if (this.quantity < 1 || this.quantity > 25) {
                    alert("Minimalna liczba worków to 1 a maksymalna to 25");
                } else {
                    result = true;
                }
            } else {
                alert("Proszę wpisać liczbę worków");
            }
            return result;
        }

        validateStep3(step) {
            this.selectedInstitution = -1;
            this.selectedInstitutionName = '';
            step.querySelectorAll('input[type="radio"]').forEach(radio => {
                if (radio.checked == true) {
                    this.selectedInstitution = radio.value;
                    this.selectedInstitutionName = radio.parentNode.querySelector('.title').textContent;
                }
            });
            if (this.selectedInstitution === -1) {
                alert('Proszę wybrać instytucję');
            }
            return this.selectedInstitution > -1;
        }

        validateStep4(step) {
            this.address = step.querySelector('#address').value;
            this.city = step.querySelector('#city').value;
            this.postcode = step.querySelector('#postcode').value;
            this.phone = step.querySelector('#phone').value;
            this.date = step.querySelector('#data').value;
            this.time = step.querySelector('#time').value;
            this.moreInfo = step.querySelector('#more-info').value;

            if (!this.address) {
                alert('Proszę wprowadzić adres odbioru');
                return false;
            }
            if (!this.city) {
                alert('Proszę wprowadzić nazwę miasta');
                return false;
            }
            if (!this.postcode) {
                alert('Proszę wprowadzić kod pocztowy');
                return false;
            }
            if (!this.phone) {
                alert('Proszę wprowadzić nr telefonu kontaktowego');
                return false;
            }
            if (!this.date) {
                alert('Proszę wprowadzić datę odbioru');
                return false;
            }
            if (!this.time) {
                alert('Proszę wprowadzić godzinę odbioru');
                return false;
            }
            return true;
        }

        sendDonationData() {
            let donation = {
                quantity: this.quantity,
                street: this.address,
                city: this.city,
                zipCode: this.postcode,
                pickUpDate: this.date,
                pickUpTime: this.time,
                pickUpComment: this.moreInfo,
                institution: {
                    id: this.selectedInstitution
                },
                categories: []
            }

            this.selectedCategories.forEach(categoryId => {
                let category = {
                    id: categoryId
                }
                donation.categories.push(category);
            });
            $.ajax({
                url: '/donation',
                method: 'POST',
                data: JSON.stringify(donation),
                contentType: 'application/json'
            }).done(function (result) {
                if (result === 'OK') {
                    document.getElementById('donation-form').submit();
                }
            });
        }
    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});
