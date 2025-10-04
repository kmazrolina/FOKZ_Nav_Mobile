# FOKZ Nav: Inertial Navigation in GPS-Denied Zones

FOKZ Nav is a mobile application that provides **inertial navigation** in environments where GPS signals are unreliable or unavailable. The system leverages the **accelerometer** and **gyroscope** sensors of Android devices, combined with a lightweight **machine learning model**, to estimate device position and orientation without relying on GPS.

---

## 🚀 Features

- **GPS-Independent Navigation**: Navigate indoors, underground, or in urban canyons where GPS fails.  
- **Real-Time Tracking**: Uses inertial measurements to continuously estimate device movement.  
- **Lightweight Mobile ML Model**: Powered by the **RoNIN model**, optimized for mobile devices using PyTorch Mobile.  
- **Randomized Testing & Simulation**: Supports inference on simulated sensor data to validate model performance.  
- **Simple Android Interface**: Single-button inference for quick testing.

---

## 📱 How It Works

1. **Data Acquisition**:  
   The app collects real-time readings from the device's accelerometer and gyroscope.  

2. **Preprocessing**:  
   Sensor data is converted into tensors of shape `(1, 6, 200)` for input into the model.  

3. **Machine Learning Inference**:  
   The **RONIN model**, a lightweight PyTorch Mobile neural network, processes the input and predicts the next movement step.  

4. **Position Estimation**:  
   Using the model output, the app updates the device’s estimated position in real-time, providing navigation feedback without GPS.

---

## 🧰 Technology Stack

- **Android Studio / Kotlin** – Mobile application development.  
- **PyTorch Mobile** – Lightweight ML inference engine for Android.  
- **RONIN ML Model** – Optimized deep learning model for inertial navigation.  
- **Sensors** – Android accelerometer and gyroscope.

---

