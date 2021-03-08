ALTER TABLE `goserver`.`user`
    ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    ADD UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
    ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
DROP INDEX `email` ,
DROP INDEX `phone_number` ,
DROP INDEX `username` ;
;
