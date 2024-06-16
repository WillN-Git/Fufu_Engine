#version 330

struct BaseLight
{
    vec3 color;
    float intensity;
};

struct DirectionalLight
{
    BaseLight base;
    vec3 direction;
};

in vec2 texCoord;
in vec3 normal;

uniform vec3 uBaseColor;
uniform vec3 uAmbientLight;
uniform sampler2D uSampler;
uniform DirectionalLight uDirectionalLight;


vec4 calcLight(BaseLight base, vec3 direction, vec3 normal)
{
    vec4 diffuseColor = vec4(0.0);

    float diffuseFactor = dot(-direction, normal);

    if (diffuseFactor > 0)
        diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;

    return diffuseColor;
}

vec4 calcDirectionalLight(DirectionalLight uDirectionalLight, vec3 normal)
{
    return calcLight(uDirectionalLight.base, -uDirectionalLight.direction, normal);
}


void main()
{
    vec4 finalColor = vec4(0.0);
    finalColor = texture2D(uSampler, texCoord.xy) * vec4(uBaseColor, 1.0);

    vec4 sun_light = vec4(1);

    vec3 n = normalize(normal);

    gl_FragColor = finalColor * sun_light * (vec4(uAmbientLight, 1.0) + calcDirectionalLight(uDirectionalLight, n));
}