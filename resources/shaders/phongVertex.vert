#version 330

layout (location = 0) in vec3 pos;
layout (location = 1) in vec2 inTexCoord;
layout (location = 2) in vec3 inNormal;

out vec4 color;
out vec2 texCoord;
out vec3 normal;

uniform mat4 uTransform;
uniform mat4 uTransformProjected;

void main()
{
    color = vec4(clamp(pos, 0.0, 1.0), 1.0);

    gl_Position = uTransformProjected * vec4(pos, 1.0);
    texCoord = inTexCoord;
    normal = (uTransform * vec4(inNormal, 0.0)).xyz;
}
