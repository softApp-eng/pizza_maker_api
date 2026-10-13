-- Ingrédients
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (1, 'Pâte fine', 'DOUGH', 2.00, true, 1, NULL);
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (2, 'Pâte classique', 'DOUGH', 2.50, true, 1, NULL);
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (3, 'Pâte épaisse', 'DOUGH', 3.00, true, 1, NULL);
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (4, 'Sauce tomate', 'SAUCE', 1.00, true, 1, 'assets/images/preview/sauce-tomate.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (5, 'Sauce crème', 'SAUCE', 1.50, true, 1, 'assets/images/preview/sauce-creme.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (6, 'Mozzarella', 'CHEESE', 1.50, true, 3, 'assets/images/preview/mozzarella.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (7, 'Chèvre', 'CHEESE', 2.00, true, 2, 'assets/images/preview/chevre.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (8, 'Pepperoni', 'MEAT', 2.00, true, 3, 'assets/images/preview/pepperoni.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (9, 'Jambon', 'MEAT', 2.00, true, 3, 'assets/images/preview/jambon.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (10, 'Poulet', 'MEAT', 2.50, true, 3, 'assets/images/preview/poulet.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (11, 'Champignons', 'VEGGIE', 0.80, true, 5, 'assets/images/preview/champignons.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (12, 'Oignons rouges', 'VEGGIE', 0.60, true, 5, 'assets/images/preview/oignons.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (13, 'Poivrons', 'VEGGIE', 0.70, true, 5, 'assets/images/preview/poivrons.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (14, 'Olives', 'TOPPING', 0.80, true, 5, 'assets/images/preview/olives.jpg');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (15, 'Origan', 'TOPPING', 0.30, true, 2, 'assets/images/preview/origan.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (16, 'Roquette', 'TOPPING', 0.70, true, 3, 'assets/images/preview/roquette.png');
INSERT INTO ingredient (id, name, category, price, is_available, max_quantity, image_url) VALUES (17, 'Miel', 'TOPPING', 0.50, true, 2, 'assets/images/preview/miel.png');

-- Pizzas prédéfinies
INSERT INTO pizza (id, name, description, image_url, base_price, is_custom, is_active) VALUES (1, 'Margherita', 'Sauce tomate, mozzarella, origan', 'assets/images/pizzas/margherita.jpg', 8.00, false, true);
INSERT INTO pizza (id, name, description, image_url, base_price, is_custom, is_active) VALUES (2, 'Regina', 'Sauce tomate, jambon, champignons, mozzarella', 'assets/images/pizzas/regina.jpg', 10.00, false, true);
INSERT INTO pizza (id, name, description, image_url, base_price, is_custom, is_active) VALUES (3, 'Pepperoni', 'Sauce tomate, pepperoni, mozzarella', 'assets/images/pizzas/pepperoni.jpg', 10.50, false, true);

-- Composition Margherita (exemple)
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (1, 1, 4, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (2, 1, 6, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (3, 1, 15, 1);

INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (4, 2, 4, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (5, 2, 6, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (6, 2, 9, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (7, 2, 11, 1);

INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (8, 3, 4, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (9, 3, 6, 1);
INSERT INTO pizza_ingredient (id, pizza_id, ingredient_id, quantity) VALUES (10, 3, 8, 1);

-- Recommandations
INSERT INTO recommendation (id, type, trigger_ingredients, suggested_ingredient_id, score) VALUES (1, 'COMPLEMENTARY', '8', 12, 0.9);
INSERT INTO recommendation (id, type, trigger_ingredients, suggested_ingredient_id, score) VALUES (2, 'COMPLEMENTARY', '8', 11, 0.8);
INSERT INTO recommendation (id, type, trigger_ingredients, suggested_ingredient_id, score) VALUES (3, 'COMPLEMENTARY', '7', 17, 0.9);
INSERT INTO recommendation (id, type, trigger_ingredients, suggested_ingredient_id, score) VALUES (4, 'COMPLEMENTARY', '9', 11, 0.8);
