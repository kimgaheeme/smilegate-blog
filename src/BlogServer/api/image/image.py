import boto3
from botocore.exceptions import ClientError

client_s3 = boto3.client(
    's3',
    aws_access_key_id="AKIATG4NLTMCN5QJ3NUA",
    aws_secret_access_key="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"
)

AWS_BUCKET = 'smilegateblogbucket'
REGION = 'ap-northeast-2'

s3 = boto3.resource('s3',
                    aws_access_key_id="AKIATG4NLTMCN5QJ3NUA",
                    aws_secret_access_key="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"
                    )
bucket = s3.Bucket(AWS_BUCKET)


async def upload(contents: bytes, name: str):
    bucket.put_object(Key=name, Body=contents)
