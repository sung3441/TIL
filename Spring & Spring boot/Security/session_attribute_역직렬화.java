List<SessionAttributeDto> sessionAttributes = selectSessionAttributes();

for (SessionAttributeDto sessionAttribute : sessionAttributes) {
    ObjectInput in = new ObjectInputStream(
        new ByteArrayInputStream(sessionAttribute.getATTRIBUTE_BYTES()));

    Object obj = in.readObject();
}
