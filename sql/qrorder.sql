-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 31, 2024 at 01:33 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qrorder`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu_items`
--

CREATE TABLE `menu_items` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_items`
--

INSERT INTO `menu_items` (`id`, `name`, `description`, `price`, `image_url`, `created_at`, `updated_at`) VALUES
(2, 'Sample Product', 'This is a sample product description.', 19.01, 'https://example.com/sample-product.jpg', '2024-08-26 11:16:42', '2024-08-28 16:03:04'),
(3, 'Spaghetti Carbonara', 'Pasta with creamy carbonara sauce, bacon, and parmesan cheese.', 10.99, 'http://example.com/images/spaghetti_carbonara.png', '2024-08-26 11:16:42', '2024-08-26 11:16:42'),
(4, 'Sample Product', 'This is a sample product description.', 19.99, 'https://example.com/sample-product.jpg', '2024-08-28 12:51:35', '2024-08-28 12:51:35'),
(5, 'Sample Product 5', 'This is a sample product description.', 19.01, 'https://example.com/sample-product.jpg', '2024-08-28 13:12:24', '2024-08-31 11:23:39'),
(6, 'Sample Product', 'This is a sample product description.', 19.99, 'https://example.com/sample-product.jpg', '2024-08-28 09:27:19', '2024-08-28 09:27:19'),
(7, 'Sample Product', 'This is a sample product description.', 19.99, 'https://example.com/sample-product.jpg', '2024-08-28 20:50:26', '2024-08-28 20:50:26'),
(8, 'Sample Product', 'This is a sample product description.', 19.99, 'https://example.com/sample-product.jpg', '2024-08-29 01:06:31', '2024-08-29 01:06:31');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `table_id` bigint(20) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `table_id`, `status`, `created_at`, `updated_at`) VALUES
(6, 8, 'RECEIVED', '2024-08-29 20:30:09', '2024-08-29 20:30:09'),
(7, 8, 'RECEIVED', '2024-08-29 21:58:17', '2024-08-29 21:58:17'),
(8, 8, 'RECEIVED', '2024-08-29 21:59:21', '2024-08-29 21:59:21');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `menu_item_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `order_id`, `menu_item_id`, `quantity`, `created_at`, `updated_at`) VALUES
(5, 7, 2, 3, '2024-08-29 21:58:17', '2024-08-29 21:58:17'),
(6, 7, 3, 1, '2024-08-29 21:58:17', '2024-08-29 21:58:17'),
(15, 6, 2, 2, '2024-08-30 01:59:22', '2024-08-30 01:59:22'),
(16, 6, 3, 1, '2024-08-30 01:59:22', '2024-08-30 01:59:22'),
(17, 8, 2, 2, '2024-08-30 01:59:35', '2024-08-30 01:59:35'),
(18, 8, 3, 1, '2024-08-30 01:59:35', '2024-08-30 01:59:35');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE `tables` (
  `id` bigint(20) NOT NULL,
  `qr_code` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`id`, `qr_code`, `created_at`, `updated_at`) VALUES
(8, 'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAABJElEQVR4Xu2WTa7CMAyE3VWOkZu2yU1zDK9iZhwECHi7JzqLjopo/XXhjH/A4i/Ze+Chi1yE+n/SDKqtRPcazodNhnR8vHa+UglXRIS4bV5niWm8IRcjEWMv45AkPa9QI8FqIzAt4YpokDUl/Z745/ycSZYaSv14CBWyvEwyMuXxPM/ZpCNQRgbG5rbzXoXMMnajDrcjD9FlSMJczF5b/nRQKgRZo+AGIwGDHuuQYKaY3TLo67MTzyeNq4WlRicenvtPhgQbEOMLOzPxl2qfTmDnzO2y8QSZuwxZypTZg+sSIcjUMCXpZfcqRfjtdDSb8TVrAeL89zSTWO4YSozA0UlTMTRaZAV6gHNcVEjcq00eGZYhzSAQvtKWqTLkuy5yEepX5Aa19jBZYiW5dQAAAABJRU5ErkJggg==', '2024-08-29 10:19:51', '2024-08-29 10:19:51'),
(9, 'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAABK0lEQVR4Xu2WQbKEIAxE44pjcFOFm3KMrMjvDuhYUzO7qU8WdqkV81y0SUDFvkneE5ce8hDq96QIlKvK5ge0hSEVp+aSjLHmmQlCaDYXGLd2BuFIT3QdkPQEy5f9MMS825L9Kb+GIecqGcen9bOOTKGc1pgbikFKMpZTvcnW9tv7LCen35krqR1hSBeOYRfZkVPZfSSDEFQU+wrMbiqHD+MRhjDUbLOotH92ez1x1w0LF+UUbMx2dXs9sbmjcBIPwyOZyTBkG04VOe95GFJkfM1YS/q/rZLlZKiqLxEE4z4GKe4Uwwjv1WEcwpC56bpwKsMQZbcrg+xvcE1iFNK5u4zrm+sQZPd/KLT9iEOM3S6cRH5s7xVdTgpi/AgkhO73VdH15LMe8hDqv8gfTrvezBnu+8EAAAAASUVORK5CYII=', '2024-08-29 04:02:29', '2024-08-29 04:02:29'),
(10, 'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAABJ0lEQVR4Xu2UQa7DIAxEnRXH4KYFbsoxvMKdMTSNqv7dV/EiIxI5fizMOEbsL8ln4tRNbkL9P6kC5aZy+IKOMKTh0VyTMda8MkEIiyVp1hngBNGI5JFYdUAyEkpeWwIR825L9l3+DkNeUzLXt/nZR5Zgp3XmpmKQmox2qjfZ+uNynu1kzPCVq6mXMATdLipIP5BjkLkvBhmpiy9McPGfsYQh3mp89YOm5jENjkKWl1iCi9mu3d5MoMYRQcmsXd7dDkA8TRcVOe95GAJHZd7KLJkxt8cgU5VzDGhtfscgs2r3ctYeiDBkblVd3xMcgKgcnmua/QTnnxiFwNGivPaKflS9n4zEK7l5UOIQW90+lNfz1dHtpCIWdpumctfp6H7yXTe5CfUr8gQv2vfUMra1agAAAABJRU5ErkJggg==', '2024-08-31 04:08:01', '2024-08-31 04:08:01');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `full_name`, `role_id`, `created_at`, `updated_at`) VALUES
(3, 'john_doe', 'password123', 'john.doe@example.com', 'John Doe', 1, '2024-08-28 10:31:01', '2024-08-28 10:31:01'),
(4, 'jane_doe', 'securepassword', 'jane.doe@example.com', 'Jane Doe', 2, '2024-08-28 19:26:58', '2024-08-28 19:26:58');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu_items`
--
ALTER TABLE `menu_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_order_items_order_id` (`order_id`),
  ADD KEY `idx_order_items_menu_item_id` (`menu_item_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `qr_code` (`qr_code`) USING HASH;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu_items`
--
ALTER TABLE `menu_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_items` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
