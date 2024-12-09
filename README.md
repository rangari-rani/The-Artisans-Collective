# The Artisans Collective

The Artisans Collective is an e-commerce platform that connects local artisans with customers to showcase and sell handcrafted products, promoting unique and sustainable goods.

![Product-List](https://github.com/rangari-rani/The-Artisans-Collective/blob/e8dbbb7beb325a0e88b5ee0a23e19abe074288cd/art1.png)
![Product-Description](https://github.com/rangari-rani/The-Artisans-Collective/blob/e8dbbb7beb325a0e88b5ee0a23e19abe074288cd/art2.png)
![Login](https://github.com/rangari-rani/The-Artisans-Collective/blob/e8dbbb7beb325a0e88b5ee0a23e19abe074288cd/art4.png)


## Tech Stack:
- **Frontend**: React.js (Vite), Redux Toolkit, React Hooks, Tailwind CSS
- **Backend**: Spring Boot
- **Database**: MySQL
- **Authentication**: JWT, Email verification (via Java Mail Sender)
- **API Requests**: Axios
- **State Management**: Redux Toolkit with Redux Thunk

## Features

### User Features:
1. **Product Management**:
   - Browse products
   - Filter and sort by categories or price
   - View product details
   - Pagination for large product lists

2. **Cart Management**:
   - Add and update items in the cart

3. **Checkout Process**:
   - Add new shipping address
   - Secure payment via Razorpay

4. **Order History**:
   - View past orders and manage cancellations

5. **Review & Rating**:
   - Write reviews and rate products

### Seller Features:
1. **Product Management**:
   - Add new products
   - Manage customer orders

2. **Payment & Transactions**:
   - Track payments
   - View transaction history

### Admin Features:
- Manage homepage content through the admin panel

## Authentication & Authorization:
- **Registration & Validation**: User details are validated using Yup, and an OTP is generated using Formic during registration.
- **Email Verification**: OTP is sent via Java Mail Sender for account verification.
- **JWT Token Authentication**: Issued after email verification for secure login.
- **Role-based Authentication**: Ensures users, sellers, and admins have appropriate access.




