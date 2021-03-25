INSERT INTO tb_product (name, description, price, img_Url, created_At) VALUES ('Notebook Samsung', 'i7 4Gb Ram', 2300, 'https://images.tcdn.com.br/img/img_prod/15959/notebook_samsung_expert_350xbe_intel_core_i5_8a_g_8gb_ddr4_hd_1tb_placa_de_video_2gb_tela_15_6_windo_15817_1_20190920161419.jpg', NOW());

INSERT INTO tb_category (name) VALUES ('Informatica');
INSERT INTO tb_category (name) VALUES ('Eletrônicos');
INSERT INTO tb_category (name) VALUES ('Livros');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1,1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1,2);
