package com.kicky.easybackpacks.core;

import com.kicky.easybackpacks.Backpack;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


public class BackpackDataType implements PersistentDataType<byte[], Backpack> {

    @Override
    public @NotNull Class<Backpack> getComplexType() {
        return Backpack.class;
    }

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public byte @NotNull [] toPrimitive(@NotNull Backpack complex, @NotNull PersistentDataAdapterContext arg1) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public @NotNull Backpack fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext arg1) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (Backpack) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
